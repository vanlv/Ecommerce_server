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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.common.CalculateDiscount;
import com.example.demo.common.Slug;
import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoRes;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.product.ProductTopSale;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.inventory.Supplier;
import com.example.demo.entity.product.Accessory;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Camera;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Technology;
import com.example.demo.entity.product.Tivi;
import com.example.demo.entity.product.Wash;
import com.example.demo.entity.promotion.ProductDiscount;
import com.example.demo.repository.AccessoryRepository;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CameraRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.ProductDiscountRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.TechnologyRepository;
import com.example.demo.repository.TiviRepository;
import com.example.demo.repository.WashRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private CategoryRepository categoryRepos;

	@Autowired
	private SubCategoryRepository subcategoryRepos;

	@Autowired
	private ImageRepository imageRepos;

	@Autowired
	private BrandRepository brandRepos;
	
	@Autowired
	private SupplierRepository supplierRepos;

	@Autowired
	private TechnologyRepository techRepos;

	@Autowired
	private CameraRepository cameraRepos;

	@Autowired
	private TiviRepository tiviRepos;

	@Autowired
	private WashRepository washRepos;
	
	@Autowired
	private AccessoryRepository accessoryRepos;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private OrderDetailRepository orderDetailRepos;
	
	@Autowired
	private ColorRepository colorRepos;
	
	@Autowired
	private ProductDiscountRepository discountRepos;
	
//	@Autowired
//	private TagRepository tagRepos;

	@Override
	public Page<ProductListDto> productList(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity." + dto.getSortBy() + " " + dto.getSortValue();
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.product.ProductListDto(entity) from  Product as entity where entity.display=1 AND (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			
			if(dto.getKeyword().contains(" ")) {
//				String[] keywords = dto.getKeyword().split(" ");
				whereClause += " AND ( entity.name LIKE " + "'" + dto.getKeyword() + "'" + " OR entity.description LIKE "+ "'" + dto.getKeyword()+ "'"
						+ " OR entity.slug LIKE "+ "'" + dto.getKeyword()+ "'" + " OR entity.category.name LIKE "+ "'" + dto.getKeyword()+ "'" + " OR entity.category.code LIKE " + "'"+ dto.getKeyword()+ "'"
						+ " OR entity.subcategory.name LIKE " + "'"+ dto.getKeyword() + "'"+ " OR entity.subcategory.code LIKE " + "'"+ dto.getKeyword()+ "')";
//				whereClause += " AND ( entity.name LIKE " + "'" + keywords[0] + "'" + " OR entity.description LIKE "+ "'" + keywords[0]+ "'"
//						+ " OR entity.slug LIKE "+ "'" + keywords[0]+ "'" + " OR entity.category.name LIKE "+ "'" + keywords[0]+ "'" + " OR entity.category.code LIKE " + "'"+ keywords[0]+ "'"
//						+ " OR entity.subcategory.name LIKE " + "'"+ keywords[0] + "'"+ " OR entity.subcategory.code LIKE " + "'"+ keywords[0]+ "'";
//				for(int i = 1; i < keywords.length; i++) {
//					whereClause += " or entity.name LIKE " + "'" +  keywords[i]+ "'" + " OR entity.description LIKE "+ "'" + keywords[i]+ "'"
//							+ " OR entity.slug LIKE "+ "'" + keywords[i]+ "'" + " OR entity.category.name LIKE "+ "'" + keywords[i]+ "'" + " OR entity.category.code LIKE "+ "'" + keywords[i]+ "'"
//							+ " OR entity.subcategory.name LIKE "+ "'" + keywords[i]+ "'" + " OR entity.subcategory.code LIKE "+ "'" + keywords[i]+ "'" ;
//				}
//				whereClause += " ) ";
			} else {
				whereClause += " AND ( entity.name LIKE :text " + "OR entity.description LIKE :text "
						+ "OR entity.slug LIKE :text " + "OR entity.slug LIKE :text "
						+ "OR entity.category.name LIKE :text " + "OR entity.category.code LIKE :text "
						+ "OR entity.subcategory.name LIKE :text " + "OR entity.subcategory.code LIKE :text )";
			}
			
		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :category )";
		}
		if (dto.getSubcategory() != null) {
			whereClause += " AND ( entity.subcategory.code LIKE :subcategory )";
		}
		
		if (dto.getRam() != null && StringUtils.hasText(dto.getRam())) {
			whereClause += " AND ( entity.technology.ram = :ram )";
		} else {
			whereClause += "";
		}
		
		if (dto.getResolution() != null && StringUtils.hasText(dto.getResolution())) {
			whereClause += " AND ( entity.technology.display_resolution = :resolution )";
		} else {
			whereClause += "";
		}
		
		if (dto.getImage() != null && StringUtils.hasText(dto.getImage())) {
			whereClause += " AND ( entity.camera.image_quality = :image )";
		} else {
			whereClause += "";
		}
		if (dto.getVideo() != null && StringUtils.hasText(dto.getVideo())) {
			whereClause += " AND ( entity.camera.video_quality = :video )";
		} else {
			whereClause += "";
		}
		
		if (dto.getIs3DTV() != null) {
			whereClause += " AND ( entity.tivi.is3D = :is_3d )";
		} else {
			whereClause += "";
		}
		if (dto.getIsSmartTV() != null) {
			whereClause += " AND ( entity.tivi.type_tv = :is_smart )";
		} else {
			whereClause += "";
		}
		
		if (dto.getBrand() != null && StringUtils.hasText(dto.getBrand())) {
			if (dto.getBrand().contains(",")) {
				String[] s = dto.getBrand().split(",");
				whereClause += " AND ( entity.brand.code = '" + s[0] + "' )";
				for (int i = 1; i < s.length; i++) {
					whereClause += " OR ( entity.brand.code = '" + s[i] + "' )";
				}
			} else {
				whereClause += " AND ( entity.brand.code = :brand )";
			}
		} else {
			whereClause += "";
		}

		if (dto.getPrice() != null && dto.getPrice().equalsIgnoreCase("") == false) {
			String[] s = dto.getPrice().toString().split(",");
			Long begin = Long.parseLong(s[0]);
			Long end = Long.parseLong(s[1]);
			whereClause += " AND ( entity.price BETWEEN " + begin + " AND " + end + " )";
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;
//		System.out.println(sql);

		Query q = manager.createQuery(sql, ProductListDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			if(dto.getKeyword().contains(" ")) {
				
			} else {
				q.setParameter("text", '%' + dto.getKeyword() + '%');
				qCount.setParameter("text", '%' + dto.getKeyword() + '%');
			}
			
		}

		if (dto.getCategory() != null) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getSubcategory() != null) {
			q.setParameter("subcategory", dto.getSubcategory());
			qCount.setParameter("subcategory", dto.getSubcategory());
		}
		
		if (dto.getRam() != null && StringUtils.hasText(dto.getRam())) {
			q.setParameter("ram", dto.getRam());
			qCount.setParameter("ram", dto.getRam());
		}
		if (dto.getResolution() != null && StringUtils.hasText(dto.getResolution())) {
			q.setParameter("resolution", dto.getResolution());
			qCount.setParameter("resolution", dto.getResolution());
		}
		
		if (dto.getImage() != null && StringUtils.hasText(dto.getImage())) {
			q.setParameter("image", dto.getImage());
			qCount.setParameter("image", dto.getImage());
		}
		if (dto.getVideo() != null && StringUtils.hasText(dto.getVideo())) {
			q.setParameter("video", dto.getVideo());
			qCount.setParameter("video", dto.getVideo());
		}
		
		if (dto.getIs3DTV() != null) {
			q.setParameter("is_3d", dto.getIs3DTV());
			qCount.setParameter("is_3d", dto.getIs3DTV());
		}
		if (dto.getIsSmartTV() != null) {
			q.setParameter("is_smart", dto.getIsSmartTV());
			qCount.setParameter("is_smart", dto.getIsSmartTV());
		}

		if (dto.getBrand() != null && dto.getBrand().length() > 0 && dto.getBrand().contains(",") == false) {
			q.setParameter("brand", dto.getBrand());
			qCount.setParameter("brand", dto.getBrand());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductListDto> entities = q.getResultList();
		Integer seller_count = 0;
		for (ProductListDto item : entities) {
			if (orderDetailRepos.countAllByProductId(item.getId()) != null) {
				seller_count += orderDetailRepos.countAllByProductId(item.getId());
			} else {
				seller_count = 0;
			}
			item.setSeller_count(seller_count);
			seller_count = 0;
		}

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductListDto> result = new PageImpl<ProductListDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ProductListDto> getAllProduct(AdvanceSearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.product.ProductListDto(entity) from  Product as entity where (1=1) ";

		if (dto.getDisplay() == 0 || dto.getDisplay() == 1) {
			whereClause += " AND ( entity.display = " + dto.getDisplay() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			whereClause += " AND ( entity.name LIKE :name " + "OR entity.description LIKE :name "
					+ "OR entity.slug LIKE :name )";
		} else {
			whereClause += "";
		}

		if (dto.getSku() != null && StringUtils.hasText(dto.getSku())) {
			whereClause += " AND ( entity.sku LIKE :sku )";
		}

		if (dto.getCategory() != null && StringUtils.hasText(dto.getCategory())) {
			whereClause += " AND ( entity.category.code = :category )";
		} else {
			whereClause += "";
		}

		if (dto.getBrand() != null && StringUtils.hasText(dto.getBrand())) {
			if (dto.getBrand().contains(",")) {
				String[] s = dto.getBrand().split(",");
				whereClause += " AND ( entity.brand.code = '" + s[0] + "' )";
				for (int i = 1; i < s.length; i++) {
					whereClause += " OR ( entity.brand.code = '" + s[i] + "' )";
				}
			} else {
				whereClause += " AND ( entity.brand.code = :brand )";
			}
		} else {
			whereClause += "";
		}
		
		if (dto.getSupplier() != null && StringUtils.hasText(dto.getSupplier())) {
			if (dto.getSupplier().contains(",")) {
				String[] s = dto.getSupplier().split(",");
				whereClause += " AND ( entity.supplier.code = '" + s[0] + "' )";
				for (int i = 1; i < s.length; i++) {
					whereClause += " OR ( entity.supplier.code = '" + s[i] + "' )";
				}
			} else {
				whereClause += " AND ( entity.supplier.code = :supplier )";
			}
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ProductListDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			q.setParameter("name", '%' + dto.getName() + '%');
			qCount.setParameter("name", '%' + dto.getName() + '%');
		}

		if (dto.getSku() != null && StringUtils.hasText(dto.getSku())) {
			q.setParameter("sku", '%' + dto.getSku() + '%');
			qCount.setParameter("sku", '%' + dto.getSku() + '%');
		}

		if (dto.getCategory() != null && dto.getCategory().length() > 0) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getBrand() != null && dto.getBrand().length() > 0 && dto.getBrand().contains(",") == false) {
			q.setParameter("brand", dto.getBrand());
			qCount.setParameter("brand", dto.getBrand());
		}
		
		if (dto.getSupplier() != null && dto.getSupplier().length() > 0 && dto.getSupplier().contains(",") == false) {
			q.setParameter("supplier", dto.getSupplier());
			qCount.setParameter("supplier", dto.getSupplier());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductListDto> entities = q.getResultList();
		Integer seller_count = 0;
		for (ProductListDto item : entities) {
			if (orderDetailRepos.countAllByProductId(item.getId()) != null) {
				seller_count = orderDetailRepos.countAllByProductId(item.getId());
			} else {
				seller_count = 0;
			}
			item.setSeller_count(seller_count);
		}

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductListDto> result = new PageImpl<ProductListDto>(entities, pageable, count);
		return result;
	}

	@Override
	public ProductDto saveOrUpdate(ProductDto dto) {
		if (dto != null) {

			Product entity = null;
			Technology tech = null;
			Camera camera = null;
			Tivi tivi = null;
			Wash wash = null;
			Accessory accessory = null;
			Image image = null;
			Inventory inventory = null;
			ProductDiscount discount = null;
//			Tag tag = null;

			Category category = categoryRepos.findOneByCode(dto.getCategory());
			SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategory());
			Brand brand = brandRepos.findOneByCode(dto.getBrand());
			Supplier supplier = supplierRepos.findOneByCode(dto.getSupplier());
			// 1 - n product - image
			List<String> imageUrls = dto.getImages();
			List<Image> images = new ArrayList<>();
			
			List<String> color_names = dto.getColors();
			List<Color> colors = new ArrayList<Color>();
			for(String item : color_names) {
				Color color = colorRepos.findOneByName(item);
				colors.add(color);
			}
			
//			List<TagDto> tagNames = dto.getTags();
//			List<Tag> tags = new ArrayList<>();
			
			List<Inventory> inventories = new ArrayList<>();

			if (dto.getId() != null) {
				entity = productRepos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());

				List<Image> imagesProduct = imageRepos.findAllByProductId(entity.getId());
				for (Image item : imagesProduct) {
					imageRepos.deleteByProductId(item.getProduct().getId());
				}

				for (int i = 0; i < imageUrls.size(); i++) {
					image = new Image(imageUrls.get(i));
					images.add(image);
				}
				discount = discountRepos.findOneByProduct(entity);
				switch (entity.getType()) {
				case 1:
					tech = techRepos.findOneByProduct(entity);
					break;
				case 2:
					camera = cameraRepos.findOneByProduct(entity);
					break;
				case 3:
					tivi = tiviRepos.findOneByProduct(entity);
					break;
				case 4:
					wash = washRepos.findOneByProduct(entity);
					break;
				case 5:
					accessory = accessoryRepos.findOneByProduct(entity);
					break;
				default:
					break;
				}

			}
			if (entity == null) {
				entity = new Product();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
				tech = new Technology();
				camera = new Camera();
				tivi = new Tivi();
				wash = new Wash();
				accessory = new Accessory();
				discount = new ProductDiscount();
				discount.setStatus(1);
				for(Color item : colors) {
//					inventory = new Inventory(0, 0, entity, item, category.getCode());
					inventory = new Inventory();
					inventory.setDisplay(1);
					inventory.setCategory_code(category.getCode());
					inventory.setQuantity_item(0);
					inventory.setTotal_import_item(0);
					inventory.setColor(item);
					inventory.setProduct(entity);
					inventories.add(inventory);
				}
				for (int i = 0; i < imageUrls.size(); i++) {
					image = new Image(imageUrls.get(i));
					images.add(image);
				}
			}
			entity.setType(dto.getType());
			entity.setName(dto.getName());
			entity.setMainIamge(dto.getMainImage());
			entity.setWeight(dto.getWeight());
			entity.setLength(dto.getLength());
			entity.setWidth(dto.getWidth());
			entity.setHeight(dto.getHeight());
			entity.setPrice(dto.getPrice());
			entity.setList_price(dto.getList_price());
			entity.setSku(dto.getSku());
			entity.setSlug(Slug.makeSlug(dto.getName()));
			entity.setDescription(dto.getDescription());
			entity.setSizeWeight(dto.getSizeWeight());
			entity.setMaterial(dto.getMaterial());
			entity.setFeatures(dto.getFeatures());
			entity.setDisplay(1);
			entity.setCategory(category);
			entity.setSubcategory(subcategory);
			entity.setBrand(brand);
			entity.setSupplier(supplier);
			entity.setDiscount(discount);
			
//			if (tagNames != null) {
//				for (TagDto item : tagNames) {
//					tag = tagRepos.getOneByCode(item.getCode());
//					if (tag != null) {
//						tags.add(tag);
//					}
//				}
//			}
			
			switch (dto.getType()) {
			case 1:
				// electric
				tech.setScreen(dto.getScreen());
				tech.setOperatorSystem(dto.getOperatorSystem());
				tech.setRam(dto.getRam());
				tech.setPin(dto.getPin());
				tech.setDesign(dto.getDesign());
				tech.setReleaseTime(dto.getReleaseTime());
				tech.setScreen_size(dto.getScreen_size());
				tech.setCamera(dto.getCamera());
				tech.setDisplay_resolution(dto.getDisplay_resolution());
				tech.setChip(dto.getChip());

				// phone
				tech.setBehindCamera(dto.getBehindCamera());
				tech.setFrontCamera(dto.getFrontCamera());
				tech.setInternalMemory(dto.getInternalMemory());
				tech.setSim(dto.getSim());
				tech.setNumber_sim(dto.getNumber_sim());
				tech.setAccessory(dto.getAccessory());

				// laptop
				tech.setCard(dto.getCard());
				tech.setCpu(dto.getCpu());
				tech.setHardWare(dto.getHardWare());
				tech.setBus(dto.getBus());
				tech.setProduct(entity);
				break;
			case 2:
				camera.setModel(dto.getModel());
				camera.setImage_processing(dto.getImage_processing());
				camera.setImage_quality(dto.getImage_quality());
				camera.setVideo_quality(dto.getVideo_quality());
				camera.setMemory_card(dto.getMemory_card());
				camera.setScreen_camera(dto.getScreen_camera());
				camera.setScreen_size_camera(dto.getScreen_size_camera());
				camera.setShutter_speed(dto.getShutter_speed());
				camera.setProduct(entity);
				break;
			case 3:
				tivi.setYear(dto.getYear());
				tivi.setDisplay_resolution_tv(dto.getDisplay_resolution_tv());
				tivi.setType_tv(dto.getType_tv());
				tivi.setApp_avaiable(dto.getApp_avaiable());
				tivi.setUsb(dto.getUsb());
				tivi.setIs3D(dto.getIs3D());
				tivi.setSpeaker(dto.getSpeaker());
				tivi.setTechlonogy_sound(dto.getTechlonogy_sound());
				tivi.setComponent_video(dto.getComponent_video());
				tivi.setHdmi(dto.getHdmi());
				tivi.setControl_by_phone(dto.getControl_by_phone());
				tivi.setImage_processing_tv(dto.getImage_processing_tv());
				tivi.setProduct(entity);
				break;
			case 4:
				wash.setWash_weight(dto.getWash_weight());
				wash.setWash_mode(dto.getWash_mode());
				wash.setIs_fast(dto.getIs_fast());
				wash.setWash_tub(dto.getWash_tub());
				wash.setIs_inverter(dto.getIs_inverter());
				wash.setType_engine(dto.getType_engine());
				wash.setProduct(entity);
				break;
			case 5:
				accessory.setAccessory_model(dto.getAccessory_model());
				accessory.setFeatute(dto.getFeature());
				accessory.setProduct(entity);
				break;
			default:
				break;
			}

			entity.setImages(images);
//			entity.setTags(tags);
			entity.setInventories(inventories);
			for (int i = 0; i < images.size(); i++) {
				images.get(i).setProduct(entity);
			}
			
			entity = productRepos.save(entity);
			entity.setDiscount(discount);
			discount.setProduct(entity);
			switch (dto.getType()) {
			case 1:
				entity.setTechnology(tech);
				tech = techRepos.save(tech);
				break;
			case 2:
				entity.setCamera(camera);
				camera = cameraRepos.save(camera);
				break;
			case 3:
				entity.setTivi(tivi);
				tivi = tiviRepos.save(tivi);
				break;
			case 4:
				entity.setWash(wash);
				wash = washRepos.save(wash);
				break;
			case 5:
				entity.setAccessory(accessory);
				accessory = accessoryRepos.save(accessory);
				break;
			default:
				break;
			}
			if (entity != null) {
				return new ProductDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Product entity = productRepos.getById(id);
			if (entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
			productRepos.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public ProductDtoRes getProductById(Long id, String color) {
		Product product = productRepos.getById(id);
		Color c = null;
		if(color != null &&color.equalsIgnoreCase("") == false) {
			c = colorRepos.findOneByName(color);
		} else {
			c = colorRepos.findOneByName(product.getInventories().get(0).getColor().getName());
		}
		Inventory inv = inventoryRepos.getOneByProductAndColor(product, c);
		ProductDtoRes dto = new ProductDtoRes(product);
		dto.setIn_stock(inv.getQuantity_item());
		return dto;
	}

	@Override
	public ProductDto getDetailProduct(Long id) {
		Product product = productRepos.getById(id);
		ProductDto dto = new ProductDto(product);
		List<String> colors = new ArrayList<>();
		for(Inventory item : product.getInventories()) {
			colors.add(item.getColor().getName());
		}
		dto.setColors(colors);
		return dto;
	}

	@Override
	public Page<ProductListDto> getAllProductByBrand(String brandCode, Integer page, Integer limit, String sortBy) {
		Brand brand = brandRepos.findOneByCode(brandCode);
		Page<Product> list = productRepos.findAllByBrand(brand,
				PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<ProductListDto> dtos = list.map(item -> new ProductListDto(item));
		return dtos;
	}

	@Override
	public List<ProductListDto> getAllByBrand(Long productId, String brandCode) {
		Brand brand = brandRepos.findOneByCode(brandCode);
		Page<Product> pages = productRepos.findAllByBrand(brand, PageRequest.of(0, 4, Sort.by("id").descending()));
		List<Product> list = pages.getContent();
		List<ProductListDto> dtos = new ArrayList<>();
		for (Product p : list) {
			if (productId != p.getId()) {
				ProductListDto dto = new ProductListDto(p);
				dtos.add(dto);
			}
		}
		Integer seller_count = 0;
		for (ProductListDto item : dtos) {
			if (orderDetailRepos.countAllByProductId(item.getId()) != null) {
				seller_count += orderDetailRepos.countAllByProductId(item.getId());
			} else {
				seller_count = 0;
			}
			item.setSeller_count(seller_count);
			seller_count = 0;
		}
		return dtos;
	}

	@Override
	public Page<ProductTopSale> topSaleProduct(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String groupOrderClause = " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ " INNER JOIN Order o ON o.status = 2 and o.id = s.order.id " + "GROUP BY s.product.id ";
		String sql = "select new com.example.demo.dto.product.ProductTopSale(p.id as id, p.name as name, "
				+ "p.slug as slug, p.price as price, p.list_price as list_price, p.mainIamge as mainImage, p.brand.name as brandName, p.brand.madeIn as brandMadeIn, "
				+ " SUM(s.quantity) as quantity_sold) " + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " INNER JOIN Order o ON o.status = 2 and o.id = s.order.id "
				+ " AND (TIMESTAMPDIFF(DAY, o.createdDate, NOW()) <= 30 )";
		sql += groupOrderClause;

		Query q = manager.createQuery(sql, ProductTopSale.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ProductTopSale> entities = q.getResultList();
		
		for(ProductTopSale item : entities) {
			if (item.getPrice() != null && item.getList_price() != null) {
				item.setPercent_discount(CalculateDiscount.countDiscount(item.getPrice(), item.getList_price()));
			} else {
				item.setPercent_discount(null);
			}
		}

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ProductTopSale> result = new PageImpl<ProductTopSale>(entities, pageable, count);
		return result;
	}

}
