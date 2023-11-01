package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.other.RecommendProduct;
import com.example.demo.utils.TF_IDFUtil;

@Service
public class ContentBasedService {

	
	public static List<RecommendProduct> similarByTags(List<String> tags, List<List<String>> documents, Boolean is_get_all) {
        List<Double> result = new ArrayList<>();
        double queryDistance = 0;
        for (int i = 0; i < tags.size(); i++) {
            double bp = Math.pow(TF_IDFUtil.TF_IDF(tags, documents, tags.get(i)), 2);  // binh phuong moi diem
            queryDistance += bp;
        }
        for (int i = 0; i < documents.size(); i++) {
            double dotProduct = 0, queryByDocument = 0, cosine_similarity = 0;
            for (int j = 0; j < tags.size(); j++) {
                dotProduct += TF_IDFUtil.TF_IDF(tags, documents, tags.get(j)) * TF_IDFUtil.TF_IDF(documents.get(i), documents, tags.get(j));
                queryByDocument += Math.pow(TF_IDFUtil.TF_IDF(documents.get(i), documents, tags.get(j)), 2);
            }
            cosine_similarity = dotProduct / (Math.sqrt(queryDistance) * Math.sqrt(queryByDocument));
            result.add(cosine_similarity);
        }
        List<RecommendProduct> items = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if(!Double.isNaN(result.get(i))) {
                items.add(new RecommendProduct(result.get(i), i));
           }
        }
        items.sort((RecommendProduct o1, RecommendProduct o2) -> o2.getValue() - o1.getValue() > 0 ? 1 : -1);
        List<RecommendProduct> listResult = new ArrayList<>();
        if(is_get_all == true) {
        	for(int i = 0; i<items.size(); i++) {
            	listResult.add(new RecommendProduct(items.get(i).getValue(), items.get(i).getIndex()));
            }
        } else {
        	for(int i = 1; i<items.size(); i++) {
            	listResult.add(new RecommendProduct(items.get(i).getValue(), items.get(i).getIndex()));
            }
        }
        return listResult;
    }
	
	 public static void main(String[] args) {
	        List<String> doc1 = Arrays.asList("người", "lên", "ngựa", "kẻ", "chia", "bào", "rừng", "phong", "thu", "đã", "nhuốm", "màu", "quan", "san");
	        List<String> doc2 = Arrays.asList("ô", "hay", "buồn", "vương", "cây", "ngô", "đồng", "vàng", "rơi", "vàng", "rơi", "thu", "mênh", "mông");
	        List<String> doc3 = Arrays.asList("một", "chiều", "về", "bên", "bến", "sông", "thu", "nghe", "tin", "em", "cưới", "á", "cái", "đù");
	        List<String> doc4 = Arrays.asList("hillthompson", "willsmith", "dec 1776");
	        List<String> doc5 = Arrays.asList("hillthompson", "willsmith", "jan 1996");
	        List<String> doc6 = Arrays.asList("hillthompson", "poopjenkins", "sept 1822");
	        List<String> doc7 = Arrays.asList("foobar", "poopjenkins", "some date");
	        List<List<String>> documents = Arrays.asList(doc1, doc2, doc3, doc4, doc5, doc6, doc7);

	        System.out.println("Result = " + similarByTags(Arrays.asList("hillthompson", "poopjenkins"), documents, false));
	    }
}
