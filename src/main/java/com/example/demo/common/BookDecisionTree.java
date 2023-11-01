package com.example.demo.common;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;

import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

public class BookDecisionTree {

	private Instances trainingData;

	public static void main(String[] args) {
		try {
			File file = new ClassPathResource("decision_tree_data/test.arff").getFile();

			BookDecisionTree decisionTree = new BookDecisionTree(file.getAbsolutePath());
			J48 tree = decisionTree.performTraining();
			System.out.println(tree.toString());
			System.out.println(tree.graph());
			System.out.println(tree.binarySplitsTipText());
			Instance testInstance = decisionTree.getTestInstance("piano", "to", "nang");
			int result = (int) tree.classifyInstance(testInstance);
			String results = decisionTree.trainingData.attribute(3).value(result);
			System.out.println("Test with: " + testInstance + "  Result: " + results);
			testInstance = decisionTree.getTestInstance("piano", "to", "nang");
			result = (int) tree.classifyInstance(testInstance);
			results = decisionTree.trainingData.attribute(3).value(result);
			System.out.println("Test with: " + testInstance + "  Result: " + results);

			// display classifier
			final javax.swing.JFrame jf = new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
			jf.setSize(500, 400);
			jf.getContentPane().setLayout(new BorderLayout());
			TreeVisualizer tv = new TreeVisualizer(null, tree.graph(), new PlaceNode2());
			jf.getContentPane().add(tv, BorderLayout.CENTER);
			jf.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					jf.dispose();
				}
			});

			jf.setVisible(true);
			tv.fitToScreen();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public BookDecisionTree(String fileName) {
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

	private Instance getTestInstance(String abc, String doon, String khoiluong) {
		Instance instance = new DenseInstance(4);
		instance.setDataset(trainingData);
		instance.setValue(trainingData.attribute(0), abc);
		instance.setValue(trainingData.attribute(1), doon);
		instance.setValue(trainingData.attribute(2), khoiluong);
		return instance;
	}
}