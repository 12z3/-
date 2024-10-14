package bS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class bsInto2D {
	public static void main(String[] args) throws IOException {
		List<List<Integer>> res = new ArrayList<>(List.of(
				List.of(1, 22, 34, 45, 15, 26, 47),
				List.of(48, 29, 10, 11, 32, 43, 14),
				List.of(18, 29, 30, 1, 2, 33, 44)
		));
		List<List<Integer>> matrix = gen649Matrix();
		int[] officialRes = {12, 3, 4, 5, 1, 9, 45};

		List<List<Integer>> newL = searchInto649Matrix(res, matrix);
		System.out.println(newL);
	}


	static void writeRes(List<List<Integer>> res) {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
		String currentTime = ldt.format(formater);
		List<List<Integer>> matrix = gen649Matrix();
		File file = new File("");


	}


	static List<List<Integer>> searchInto649Matrix(List<List<Integer>> res, List<List<Integer>> matrix) throws IOException {
		LocalDateTime ldt = LocalDateTime.now();
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
		String currentTime = ldt.format(formater);
		File file = new File("");
		FileWriter writer = new FileWriter(file);


		List<List<Integer>> matches = List.of();
		List<Integer> tmpList = List.of();
		List<Integer> memo = List.of();


		matches = new ArrayList<>();

		for (int i = 0; i < res.size(); i++) {
			List<Integer> row = res.get(i);
			memo = new ArrayList<>();
			for (int j = 0; j < matrix.size(); j++) {
			tmpList = new ArrayList<>();
				for (int k = 0; k < row.size(); k++) {
					int el = bs(matrix.get(j), row.get(k));
					if (el != -1){
						tmpList.add(el);
						memo.add(el);
					} else if (!memo.contains(el)) {
						tmpList.add(0);
					}
				}
				writeLine(tmpList, writer);
				matches.add(tmpList);
			}
			writer.write("\n");
		}

		writer.close();
		return matches;
	}

	static void writeLine(List<Integer> row, FileWriter writer) throws IOException {
		for (int i = 0; i < row.size(); i++) {
			if (row.get(i) != 0) {
				writer.write(row.get(i) + " ");
			} else writer.write(" - ");
		}
		writer.write("\n");
	}

	static int bs(List<Integer> matrix, int target) {
		int s = 0, e = matrix.size() - 1, m;

		while (s <= e) {
			m = s + (e - s) / 2;

			if (matrix.get(m) == target) {
				return matrix.get(m);
			} else if (matrix.get(m) < target) {
				s = m + 1;
			} else e = m - 1;
		}
		return -1;
	}


	static List<List<Integer>> gen649Matrix() {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> row;
		int idx = 1;
		for (int i = 0; i < 7; i++) {
			row = new ArrayList<>();
			for (int j = 0; j < 7; j++) {
				row.add(idx++);
			}
			res.add(row);
		}
		return res;
	}
}
