package com.example.demo.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class DecisionTree {

	private Instances trainingData;

	public DecisionTree(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			trainingData = new Instances(reader);
			trainingData.setClassIndex(trainingData.numAttributes() - 1);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private J48 performTraining() {
		J48 j48 = new J48();
		String[] options = { "-U" };
		try {
			j48.setOptions(options);
			j48.buildClassifier(trainingData);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return j48;
	}

//	private Instance getTestInstance(String loai, String doon, String khoiluong) {
//		Instance instance = new DenseInstance(4);
//		instance.setDataset(trainingData);
//		instance.setValue(trainingData.attribute(0), loai);
//		instance.setValue(trainingData.attribute(1), doon);
//		instance.setValue(trainingData.attribute(2), khoiluong);
//		return instance;
//	}

	private Instance getTestInstance1(List<String> values) {
		Instance instance = new DenseInstance(4);
		instance.setDataset(trainingData);
		for (int i = 0; i < values.size(); i++) {
			instance.setValue(trainingData.attribute(i), values.get(i));
		}
		return instance;
	}

	public static Boolean resultDecisionTree(String type, List<String> values) throws Exception, IOException {
		File file = null;
		switch (type) {
		case "laptop":
			file = new ClassPathResource("decision_tree_data/laptop.arff").getFile();
			break;
		case "dien-thoai":
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			values = Arrays.asList("brand", "ram", "memory", "type_screen");
			break;
		case "dienthoai":
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			break;
		case "tivi":
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			break;
		case "maygiat":
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			break;
		case "phukien":
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			break;
		default:
			file = new ClassPathResource("decision_tree_data/test.arff").getFile();
			break;
		}
		DecisionTree decisionTree = new DecisionTree(file.getAbsolutePath());
		J48 tree = decisionTree.performTraining();
		Instance testInstance = decisionTree.getTestInstance1(values);
		int result = (int) tree.classifyInstance(testInstance);
		String results = decisionTree.trainingData.attribute(values.size()).value(result);
//		System.out.println("Test with: " + testInstance + "  Result: " + results);
		Boolean kq;
		if (results.toString().equalsIgnoreCase("no")) {
			kq = false;
		} else {
			kq = true;
		}
		return kq;

	}

	public static void main(String[] args) throws IOException, Exception {
//		List<String> values = new ArrayList<String>();
//		values.add("macbook");
//		values.add("16GB");
//		values.add("256GB");
//		values.add("FHD");
//		resultDecisionTree("laptop", values);
	}
}
