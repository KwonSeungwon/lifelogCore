package lifelogcollector;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomNumberProducer {

	public static ArrayList<String> ejin(int a) {

		ArrayList<String> arr = new ArrayList<String>();

		int nam = 0;

		while (true) {
			nam = a % 2;
			a = a / 2;
			String abc = Integer.toString(nam);
			arr.add(abc);

			if (a == 1) {
				arr.add(Integer.toString(a));
				break;
			}

		}

		for (int i = arr.size() - 1; i == 0; i--) {
			String acc = arr.get(i);
			arr.add(acc);

		}

		return arr;

	}

	public static ArrayList<String> ejin2(int b) {

		ArrayList<String> arr2 = new ArrayList<String>();

		int nam = 0;

		while (true) {
			nam = b % 2;
			b = b / 2;
			String abc = Integer.toString(nam);
			arr2.add(abc);

			if (b == 1) {
				arr2.add(Integer.toString(b));
				break;
			}

		}

		for (int i = arr2.size() - 1; i == 0; i--) {
			String acc = arr2.get(i);
			arr2.add(acc);

		}

		return arr2;

	}

	public static ArrayList<Integer> rere(ArrayList result, ArrayList result2) {

		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		ArrayList<String> dab = new ArrayList<String>();
		ArrayList<Integer> mok = new ArrayList<Integer>();

		int a = 0;

		for (int i = 0; i < result.size(); i++) {
			String ab = (String) result.get(i);
			int abc = Integer.parseInt(ab);
			l1.add(abc);
		}

		for (int i = 0; i < result2.size(); i++) {

			String ad = (String) result2.get(i);
			int abc = Integer.parseInt(ad);
			l2.add(abc);

		}

		for (int i = 0; i < l1.size(); i++) {
			a = l1.get(i) + l2.get(i);
			mok.add(a);
		}

		return mok;

	}

	public static void main(String[] args) {

		Random ran = new Random();

		int a = ran.nextInt(31);
		int b = ran.nextInt(31);
		System.out.println("积己等 抄荐 :" + a);
		System.out.println("积己等 抄荐2 :" + a);
		ArrayList<String> result = RandomNumberProducer.ejin(a);
		ArrayList<String> result2 = RandomNumberProducer.ejin2(a);
		ArrayList<Integer> momo = RandomNumberProducer.rere(result, result2);
		
		for (int i = 0 ; i<momo.size(); i++){
			System.out.println(momo.get(i));
		}

	}

}
