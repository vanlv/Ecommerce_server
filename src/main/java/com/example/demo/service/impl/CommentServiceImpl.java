package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.user.CommentDto;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.Comment;
import com.example.demo.entity.user.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private CommentRepository repos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private UserRepository userRepos;

	@Override
	public MessageResponse createComment(CommentDto dto) {
		if (dto != null) {
			Comment entity = null;

			if (entity == null) {
				entity = new Comment();
			}

			Product product = productRepos.getById(dto.getProductId());
			User user = userRepos.findOneByUsername(dto.getUsername());
			if (repos.existsByProductAndUser(product, user)) {
				return new MessageResponse("Bạn đã đánh giá sản phẩm này rồi!");
			} else {
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				entity.setDate_comment(dto.getDate_comment());
				entity.setContent(dto.getContent());
				entity.setRating(dto.getRating());
				entity.setDisplayName(dto.getDisplayName());
				entity.setUser(user);
				entity.setProduct(product);
				entity.setDisplay(0);

				entity = repos.save(entity);

				return new MessageResponse(
						"Cảm ơn bạn đã đánh giá. Chúng tôi sẽ thông báo đến bạn khi đánh giá được duyệt!");
			}
		}
		return null;
	}

	@Override
	public List<CommentDto> getAllCommentByUser(String username) {
		List<CommentDto> list = new ArrayList<CommentDto>();
		User user = userRepos.findOneByUsername(username);
		List<Comment> entities = repos.findAllByUser(user);
		for (Comment entity : entities) {
			CommentDto dto = new CommentDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<CommentDto> getAllCommentByProduct(Long productId) {
		List<CommentDto> list = new ArrayList<CommentDto>();
		Product product = productRepos.getById(productId);
		List<Comment> entities = repos.findAllByProduct(product);
		for (Comment entity : entities) {
			if(entity.getDisplay() == 1) {
				CommentDto dto = new CommentDto(entity);
				list.add(dto);
			}
		}

		return list;
	}

	@Override
	public Page<CommentDto> getAllComments(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.date_comment DESC";
		String sqlCount = "select count(entity.id) from  Comment as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.user.CommentDto(entity) from  Comment as entity where (1=1)  ";
		if (dto.getProductId() != null) {
			whereClause += "AND entity.display=1 AND ( entity.content LIKE :text )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, CommentDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<CommentDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<CommentDto> result = new PageImpl<CommentDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Integer countAllCommentByProduct(Long productId) {
		// TODO Auto-generated method stub
		Product product = productRepos.getById(productId);
		Integer count = repos.countAllByProduct(product);
		if (count != null) {
			return count;
		} else {
			return 0;
		}
	}

	@Override
	public MessageResponse displayComment(CommentDto dto) {
		if (dto != null) {
			Comment entity = repos.getById(dto.getId());
			entity.setDisplay(1);
			repos.save(entity);
			return new MessageResponse("Xác nhận hiển thị bình luận thành công!");
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CommentDto getOne(Long id) {
		Comment entity = repos.getById(id);
		CommentDto dto = new CommentDto(entity);
		return dto;
	}

}
