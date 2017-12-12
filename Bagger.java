package edu.wit.comp2000.jasonfagerberg.application1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Bagger
	{
		/**
		 * Scan file, put into an array of strings called inputs Take array
		 * break it down into components of items build array of items
		 * 
		 * sorts by breakability, lowest breakability first
		 * 
		 * @return sorted array of items
		 */
		private ArrayList<Item> createItems(String input, String classify)
			{
				// Where scanner / parser will go
				ArrayList<Item> order = new ArrayList();
				String name;
				String size;
				String weight;
				String firmness;
				String flex;
				String breakability;
				String classification;

				// parser
				String[] inputs = null;
				try
					{
						inputs = input.split("\\s+");
					}
				catch (PatternSyntaxException ex)
					{

					}
				// create array of items
				for (int i = 0; i < inputs.length; i += 7)
					{
						// Only add the specified classification
						// perishable or not
						classification = inputs[i + 6];
						if (classification.equals(classify))
							{
								name = inputs[i];
								size = inputs[i + 1];
								weight = inputs[i + 2];
								firmness = inputs[i + 3];
								flex = inputs[i + 4];
								breakability = inputs[i + 5];
								classification = inputs[i + 6];
								order.add(new Item(name, size, weight, firmness, flex, breakability, classification));
							}
					}

				// sort by breakability
				int n = order.size();
				int k;
				Item temp;
				for (int m = n; m >= 0; m--)
					{
						for (int i = 0; i < n - 1; i++)
							{
								k = i + 1;
								if (order.get(i).getBreakFactor() > order.get(k).getBreakFactor())
									{
										temp = order.get(i);
										order.set(i, order.get(k));
										order.set(k, temp);
									}
							}
					}
				// return sorted list
				return order;
			}

		/**
		 * to fill array bag
		 * 
		 * @param order
		 * @return
		 */
		private ArrayList<ArrayBag<Item>> fillArrayBags(ArrayList<Item> order)
			{
				ArrayList<ArrayBag<Item>> bags = new ArrayList<>();
				int filled = 0;
				bags.add(new ArrayBag<Item>());
				int bagNum = 0;
				for (int i = order.size() - 1; i >= 0; i--)
					{
						bags.get(bagNum).add(order.get(i));
						filled += order.get(i).getFillFactor();
						if (i - 1 != -1 && filled + order.get(i - 1).getFillFactor() > 15)
							{
								bags.add(new ArrayBag<Item>());
								filled = 0;
								bagNum++;
							}
					}
				return bags;
			}

		/**
		 * to Fill linked bag
		 * 
		 * @param order
		 * @return
		 */
		private ArrayList<LinkedBag<Item>> fillLinkedBags(ArrayList<Item> order)
			{
				ArrayList<LinkedBag<Item>> bags = new ArrayList<>();
				int filled = 0;
				bags.add(new LinkedBag<Item>());
				int bagNum = 0;
				for (int i = order.size() - 1; i >= 0; i--)
					{
						bags.get(bagNum).add(order.get(i));
						filled += order.get(i).getFillFactor();
						if (i - 1 != -1 && filled + order.get(i - 1).getFillFactor() > 15)
							{
								bags.add(new LinkedBag<Item>());
								filled = 0;
								bagNum++;
							}
					}
				return bags;
			}

		/**
		 * to fill Resizeable Array bag
		 * 
		 * @param order
		 * @return
		 */
		private ArrayList<ResizableArrayBag<Item>> fillResizableBags(ArrayList<Item> order)
			{
				ArrayList<ResizableArrayBag<Item>> bags = new ArrayList<>();
				int filled = 0;
				bags.add(new ResizableArrayBag<Item>());
				int bagNum = 0;
				for (int i = order.size() - 1; i >= 0; i--)
					{
						bags.get(bagNum).add(order.get(i));
						filled += order.get(i).getFillFactor();
						if (i - 1 != -1 && filled + order.get(i - 1).getFillFactor() > 15)
							{
								bags.add(new ResizableArrayBag<Item>());
								filled = 0;
								bagNum++;
							}
					}
				return bags;
			}

		/**
		 * toString for ArrayBag
		 * 
		 * @param bag
		 * @return
		 */
		public String displayArray(ArrayList<ArrayBag<Item>> bag)
			{
				String bagMark = "___________________BAG___________________\n";
				String res = "";
				String toReturn = "";
				for (int index = 0; index < bag.size(); index++)
					{
						Object[] arr = bag.get(index).toArray();
						for (int i = 0; i < arr.length; i++)
							{
								res += ((Item) arr[i]).getName() + "\n";
							}
						toReturn += bagMark;
						toReturn += res;
						res = "";
					}

				return toReturn;
			}

		/**
		 * toString for Linked Bag
		 * 
		 * @param bag
		 * @return
		 */
		public String displayLinked(ArrayList<LinkedBag<Item>> bag)
			{
				String bagMark = "___________________BAG___________________\n";
				String res = "";
				String toReturn = "";
				for (int index = 0; index < bag.size(); index++)
					{
						Object[] arr = bag.get(index).toArray();
						for (int i = 0; i < arr.length; i++)
							{
								res += ((Item) arr[i]).getName() + "\n";
							}
						toReturn += bagMark;
						toReturn += res;
						res = "";
					}

				return toReturn;
			}

		/**
		 * toString for Resizable Array Bag
		 * 
		 * @param bag
		 * @return
		 */
		public String displayResizable(ArrayList<ResizableArrayBag<Item>> bag)
			{
				String bagMark = "___________________BAG___________________\n";
				String res = "";
				String toReturn = "";
				for (int index = 0; index < bag.size(); index++)
					{
						Object[] arr = bag.get(index).toArray();
						for (int i = 0; i < arr.length; i++)
							{
								res += ((Item) arr[i]).getName() + "\n";
							}
						toReturn += bagMark;
						toReturn += res;
						res = "";
					}

				return toReturn;
			}

		public static void main(String[] args)
			{
				// Read file to string
				String inputString = "";
				ArrayList<String> arrayInputs = new ArrayList<String>();
				try
					{
						File scannedFile = new File(
								"/Users/Jason Fagerberg/Dropbox/workspace/Lab02/src/edu/wit/comp2000/jasonfagerberg/application1/groceries-1.txt");
						if (scannedFile.canRead())
							{
								System.out.println("file read");
							}
						else
							System.out.println("no file found");
						Scanner input = new Scanner(scannedFile);
						while (input.hasNextLine())
							{
								String line = input.nextLine();
								inputString += line;
							}

					}
				catch (FileNotFoundException e)
					{

						e.printStackTrace();
					}
				// End scan
				Bagger susan = new Bagger();
				ArrayList<Item> perishable = susan.createItems(inputString, "perishable");
				ArrayList<Item> nonperishable = susan.createItems(inputString, "nonperishable");
				// ARRAY BAG
				System.out.println(
						"\n                                                                                       ARRAY BAG\n\n");

				System.out.println(
						"___________________________________________________Perishable Bags___________________________________________________\n");
				System.out.println(susan.displayArray(susan.fillArrayBags(perishable)));
				System.out.println(
						"__________________________________________________Nonperishable Bags__________________________________________________\n");
				System.out.println(susan.displayArray(susan.fillArrayBags(nonperishable)));
				// LINKED BAG
				System.out.println(
						"\n                                                                                      LINKED BAG\n\n");
				System.out.println(
						"___________________________________________________Perishable Bags___________________________________________________\n");
				System.out.println(susan.displayLinked(susan.fillLinkedBags(perishable)));
				System.out.println(
						"__________________________________________________Nonperishable Bags__________________________________________________\n");
				System.out.println(susan.displayLinked(susan.fillLinkedBags(nonperishable)));
				// RESIZABLE BAG
				System.out.println(
						"\n                                                                                      RESIZABLE ARRAY BAG\n\n");
				System.out.println(
						"___________________________________________________Perishable Bags___________________________________________________\n");
				System.out.println(susan.displayResizable(susan.fillResizableBags(perishable)));
				System.out.println(
						"__________________________________________________Nonperishable Bags__________________________________________________\n");
				System.out.println(susan.displayResizable(susan.fillResizableBags(nonperishable)));
				System.out.println("Done");

			}


	}
