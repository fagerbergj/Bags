package edu.wit.comp2000.jasonfagerberg.application1;

public class Item
	{
		private String name;
		private String size;
		private String weight;
		private String firmness;
		private String flex;
		private String breakability;
		private String classification;
		private int fillFactor;
		private int breakFactor;

		public Item(String name, String size, String weight, String firmness, String flex, String breakability,
				String classification)
			{
				this.name = name;
				this.size = size;
				this.weight = weight;
				this.firmness = firmness;
				this.flex = flex;
				this.breakability = breakability;
				this.classification = classification;
				setFillFactor();
				setBreakFactor();
			}

		/**
		 * Takes size and weight and assigns a integer that determines how much
		 * space an object needs in the bag Max is 6 which is a big heavy object
		 * Min is 2 which is a small light object
		 */
		private void setFillFactor()
			{
				if (weight.equals("light"))
					{
						fillFactor++;
					}
				else if (weight.equals("medium"))
					{
						fillFactor += 2;
					}
				else
					{
						fillFactor += 3;
					}

				if (size.equals("medium"))
					{
						fillFactor += 2;
					}
				else if (size.equals("large"))
					{
						fillFactor += 3;
					}
			}

		public int getFillFactor()
			{
				return fillFactor;
			}

		/**
		 * puts things in either squish or break category
		 * 
		 * Squish is high when item is soft, flexable, and light EX bread
		 * 
		 * Break is high when an item is soft/firm (There are no soft items that
		 * don't squish) rigid and small
		 */
		private void setBreakFactor()
			{
				boolean squish;
				// sees if it breaks or if it squishes
				if (breakability.equals("nonbreakable"))
					{
						squish = true;
					}
				else
					{
						squish = false;
					}
				if (squish)
					{
						if (firmness.equals("soft"))
							{
								breakFactor += 3;
							}
						else if (firmness.equals("firm"))
							{
								breakFactor += 1;
							}

						if (flex.equals("flexible"))
							{
								breakFactor += 2;
							}
						if (weight.equals("light"))
							{
								breakFactor += 3;
							}
						else if (weight.equals("medium"))
							{
								breakFactor++;
							}
						else if (weight.equals("heavy") && size.equals("large"))
							{
								// if heavy and not breakable put it on the
								// bottom so nothing else gets squished
								breakFactor = -1;
							}
					}
				else
					{
						if (firmness.equals("firm") || firmness.equalsIgnoreCase("soft"))
							{
								breakFactor += 3;
							}
						else
							{
								breakFactor++;
							}
						if (flex.equals("rigid"))
							{
								breakFactor += 3;
							}
						else
							{
								breakFactor++;
							}

					}
			}

		public int getBreakFactor()
			{
				return breakFactor;
			}

		public String getClassification()
			{
				return classification;
			}

		public String getName()
			{
				return name;
			}

		@Override
		public String toString()
		{
			return name + " " + size + " " + weight + " " + firmness + " " + flex + " " + breakability + " " + classification;
		}

	}
