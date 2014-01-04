/**
 * 
 */
package com.shrikanthavale.salesmanagement.bestsolutionalgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shrikant Havale
 * 
 *         This class is responsible for creating a memory structure for storing
 *         the nodes, their connections or weighted path between other nodes.
 *         Based on these data a matrix representation will be created for
 *         storing the number of Hops required to travel between two nodes
 * 
 */
public class WeightedCustomerGraph {

	/**
	 * matrix to store the distance between nodes (weight of the edges)
	 */
	private int[][] nodeEdgeWeightMatrix = new int[18][18];

	/**
	 * Map for storing the position of customer node
	 */
	private HashMap<Integer, String> postionCustomerNode = new HashMap<Integer, String>();

	/**
	 * node label and max amount map
	 */
	private Map<String, Integer> nodeMaxAmountMap;

	/**
	 * traversal time from one node to another in minutes
	 */
	private static final Integer NODE_TRAVERSAL_TIME = 15;

	/**
	 * traversal time for visiting one node in minutes
	 */
	private static final Integer NODE_VISIT_TIME = 30;

	/**
	 * total time in hours
	 */
	private static final Integer TOTAL_TIME = 9;

	/**
	 * total amount earned
	 */
	private int totalAmountEarned = 0;

	/**
	 * Stores the position of all the customer nodes in the form row number and
	 * column number
	 * 
	 * @param orderNodesAdded
	 *            order in which nodes are added
	 */
	public void calculatePositionCustomerNodes(List<Integer> orderNodesAdded) {

		// initialize the row number
		int rowNumber = 0;

		// initialize the column number
		int columnNumber = 1;

		// get the grid position of each node
		for (Integer temp : orderNodesAdded) {
			rowNumber = rowNumber + 1;
			if (rowNumber > 6) {
				rowNumber = 1;
				columnNumber = columnNumber + 1;
			}

			// save the position
			postionCustomerNode.put(temp, rowNumber + ":" + columnNumber);
		}

	}

	/**
	 * Create a matrix containing the number of nodes between every other node.
	 * Matrix representing 17 * 17 (16 nodes and 1 start node)
	 * 
	 * @param orderNodesAdded
	 *            order in which nodes were added
	 */
	public void createNodeWeightMatrix(List<Integer> orderNodesAdded) {

		for (Integer baseCustomer : postionCustomerNode.keySet()) {

			boolean customerStartNode = checkCustomerStartNode(baseCustomer);
			if (customerStartNode) {
				for (Integer temporaryCustomer : postionCustomerNode.keySet()) {
					// check for the customer or start node
					customerStartNode = checkCustomerStartNode(temporaryCustomer);

					if (customerStartNode) {
						// create the edge value
						createNodeEdgeWeightMatrix(
								baseCustomer,
								temporaryCustomer,
								Integer.parseInt(postionCustomerNode.get(
										temporaryCustomer).split(":")[0]),
								Integer.parseInt(postionCustomerNode.get(
										temporaryCustomer).split(":")[1]));
					}
				}
			}
		}
	}

	/**
	 * Generate the travel path based on the matrix generated above. Get the
	 * best travel path with maximum amount of money.
	 * 
	 * @return {@link List} integers containing the nodes in order in which they
	 *         should be traveled
	 */
	public List<Integer> getTravelPath() {

		// List of traveled nodes in order
		List<Integer> travelPath = new ArrayList<Integer>();

		// list of non customer nodes for highlighting
		List<Integer> nonCustomerNodes = new ArrayList<Integer>();

		// initialize current customer to 17 as the start node
		int currentCustomer = 17;

		// one customer prior to current customer
		int previousCustomer = 17;

		// row array representing one customer
		int[] rowArray = null;

		// total time spent
		int totalTimeSpent = 0;

		while (totalTimeSpent < (TOTAL_TIME * 4 * NODE_TRAVERSAL_TIME)) {

			// add the current customer to travels list
			travelPath.add(currentCustomer);

			// start traveling from start node located at 17th position
			rowArray = nodeEdgeWeightMatrix[currentCustomer];

			// distance between two nodes maximum can be 11
			int minimumValue = 11;

			// list of minimum nodes
			List<Integer> minimumValueNodes = new ArrayList<Integer>();

			// cost of travel
			int costTravel = 0;

			// logic for finding the next minimum distance customer node
			for (int customerNode = 1; customerNode < 18; customerNode++) {
				if (rowArray[customerNode] < minimumValue
						&& !travelPath.contains(customerNode)) {

					minimumValue = rowArray[customerNode];
					minimumValueNodes.clear();
					minimumValueNodes.add(customerNode);
					costTravel = rowArray[customerNode];

				} else if (rowArray[customerNode] == minimumValue
						&& !travelPath.contains(customerNode)) {
					minimumValueNodes.add(customerNode);
				}
			}

			// capture the previous customer
			previousCustomer = currentCustomer;

			// out of many list select the customer having maximum amount
			currentCustomer = getCustomerHighAmount(minimumValueNodes);

			// calculate the time spent within the interval of 15 for traversing
			// and 30 slot for visiting the customer
			totalTimeSpent = totalTimeSpent
					+ (costTravel * NODE_TRAVERSAL_TIME) + NODE_VISIT_TIME;

			// get the non customer nodes in between them, to make traversal
			// path complete
			nonCustomerNodes.addAll(getCustomerBetweenNodes(previousCustomer,
					currentCustomer));

		}

		travelPath.addAll(nonCustomerNodes);

		return travelPath;

	}

	/**
	 * Try to get non customer nodes that fill the gap, so that their background
	 * can be painted
	 * 
	 * @param previousCustomer
	 *            previous visited customer
	 * @param currentCustomer
	 *            current visit customer
	 */
	private List<Integer> getCustomerBetweenNodes(int previousCustomer,
			int currentCustomer) {

		// get the position of previous customer
		int previousRow = Integer.parseInt(postionCustomerNode.get(
				previousCustomer).split(":")[0]);
		int previousColumn = Integer.parseInt(postionCustomerNode.get(
				previousCustomer).split(":")[1]);
		int currentRow = Integer.parseInt(postionCustomerNode.get(
				currentCustomer).split(":")[0]);
		int currentColumn = Integer.parseInt(postionCustomerNode.get(
				currentCustomer).split(":")[1]);

		int rowDifferences = 0;
		int columnDifferences = 0;
		List<String> listPosition = new ArrayList<String>();

		if (previousRow > currentRow) {
			rowDifferences = previousRow - currentRow;
			for (int i = 1; i <= rowDifferences; i++) {
				String position = new String((previousRow - i) + ":"
						+ previousColumn);
				listPosition.add(position);
			}
			if (previousColumn > currentColumn) {
				columnDifferences = previousColumn - currentColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(currentRow + ":"
							+ (previousColumn - i));
					listPosition.add(position);
				}
			} else if (previousColumn < currentColumn) {
				columnDifferences = currentColumn - previousColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(currentRow + ":"
							+ (previousColumn + i));
					listPosition.add(position);
				}
			} else if (previousColumn == currentColumn) {
				columnDifferences = 0;
			}

		} else if (previousRow < currentRow) {
			rowDifferences = currentRow - previousRow;
			for (int i = 1; i <= rowDifferences; i++) {
				String position = new String((previousRow + i) + ":"
						+ previousColumn);
				listPosition.add(position);
			}
			if (previousColumn > currentColumn) {
				columnDifferences = previousColumn - currentColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(currentRow + ":"
							+ (previousColumn - i));
					listPosition.add(position);
				}
			} else if (previousColumn < currentColumn) {
				columnDifferences = currentColumn - previousColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(currentRow + ":"
							+ (previousColumn + i));
					listPosition.add(position);
				}
			} else if (previousColumn == currentColumn) {
				columnDifferences = 0;
			}

		} else if (previousRow == currentRow) {
			if (previousColumn > currentColumn) {
				columnDifferences = previousColumn - currentColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(previousRow + ":"
							+ (previousColumn - i));
					listPosition.add(position);
				}
			} else if (previousColumn < currentColumn) {
				columnDifferences = currentColumn - previousColumn;
				for (int i = 1; i <= columnDifferences; i++) {
					String position = new String(previousRow + ":"
							+ (previousColumn + i));
					listPosition.add(position);
				}
			} else if (previousColumn == currentColumn) {
				columnDifferences = 0;
			}
		}

		return getNonCustomerNodesFromPostion(listPosition);

	}

	private List<Integer> getNonCustomerNodesFromPostion(
			List<String> listPosition) {

		List<Integer> listNonCustomerNodes = new ArrayList<Integer>();

		for (String temp : listPosition) {
			for (Integer customerNode : postionCustomerNode.keySet()) {
				if (temp.equals(postionCustomerNode.get(customerNode))) {
					listNonCustomerNodes.add(customerNode);
					break;
				}
			}
		}

		return listNonCustomerNodes;

	}

	private int getCustomerHighAmount(List<Integer> minimumValueNodes) {

		int customerWithMaximumAmount = 0;

		int finalAmount = 0;

		for (int currentCustomer : minimumValueNodes) {

			int currentAmount = 0;

			switch (currentCustomer) {

			case 1:
				currentAmount = nodeMaxAmountMap.get("A");
				break;
			case 2:
				currentAmount = nodeMaxAmountMap.get("B");
				break;
			case 3:
				currentAmount = nodeMaxAmountMap.get("C");
				break;
			case 4:
				currentAmount = nodeMaxAmountMap.get("D");
				break;
			case 5:
				currentAmount = nodeMaxAmountMap.get("E");
				break;
			case 6:
				currentAmount = nodeMaxAmountMap.get("F");
				break;
			case 7:
				currentAmount = nodeMaxAmountMap.get("G");
				break;
			case 8:
				currentAmount = nodeMaxAmountMap.get("H");
				break;
			case 9:
				currentAmount = nodeMaxAmountMap.get("I");
				break;
			case 10:
				currentAmount = nodeMaxAmountMap.get("J");
				break;
			case 11:
				currentAmount = nodeMaxAmountMap.get("K");
				break;
			case 12:
				currentAmount = nodeMaxAmountMap.get("L");
				break;
			case 13:
				currentAmount = nodeMaxAmountMap.get("M");
				break;
			case 14:
				currentAmount = nodeMaxAmountMap.get("N");
				break;
			case 15:
				currentAmount = nodeMaxAmountMap.get("O");
				break;
			case 16:
				currentAmount = nodeMaxAmountMap.get("P");
				break;
			}

			if (currentAmount > finalAmount) {
				finalAmount = currentAmount;
				customerWithMaximumAmount = currentCustomer;
			}

		}

		totalAmountEarned = totalAmountEarned + finalAmount;

		return customerWithMaximumAmount;

	}

	/**
	 * @return the totalAmountEarned
	 */
	public int getTotalAmountEarned() {
		return totalAmountEarned;
	}

	/**
	 * @param totalAmountEarned
	 *            the totalAmountEarned to set
	 */
	public void setTotalAmountEarned(int totalAmountEarned) {
		this.totalAmountEarned = totalAmountEarned;
	}

	private void createNodeEdgeWeightMatrix(Integer customer,
			int temporaryCustomer, int rowNumberTemporaryCustomer,
			int columnNumberTemporaryCustomer) {

		// distance between same customers should be set to 100
		if (customer == temporaryCustomer) {
			nodeEdgeWeightMatrix[customer][temporaryCustomer] = 100;
		} else {
			int rowNumberCustomer = Integer.parseInt(postionCustomerNode.get(
					customer).split(":")[0]);
			int columnNumberCustomer = Integer.parseInt(postionCustomerNode
					.get(customer).split(":")[1]);

			if (columnNumberCustomer == columnNumberTemporaryCustomer) {
				if (rowNumberCustomer > rowNumberTemporaryCustomer) {
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = rowNumberCustomer
							- rowNumberTemporaryCustomer;
				} else {
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = rowNumberTemporaryCustomer
							- rowNumberCustomer;
				}
			} else if (columnNumberCustomer > columnNumberTemporaryCustomer) {
				int numberHops = columnNumberCustomer
						- columnNumberTemporaryCustomer;
				if (rowNumberCustomer == rowNumberTemporaryCustomer) {
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				} else if (rowNumberCustomer > rowNumberTemporaryCustomer) {
					numberHops = numberHops
							+ (rowNumberCustomer - rowNumberTemporaryCustomer);
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				} else if (rowNumberCustomer < rowNumberTemporaryCustomer) {
					numberHops = numberHops
							+ (rowNumberTemporaryCustomer - rowNumberCustomer);
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				}

			} else if (columnNumberCustomer < columnNumberTemporaryCustomer) {
				int numberHops = columnNumberTemporaryCustomer
						- columnNumberCustomer;
				if (rowNumberCustomer == rowNumberTemporaryCustomer) {
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				} else if (rowNumberCustomer > rowNumberTemporaryCustomer) {
					numberHops = numberHops
							+ (rowNumberCustomer - rowNumberTemporaryCustomer);
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				} else if (rowNumberCustomer < rowNumberTemporaryCustomer) {
					numberHops = numberHops
							+ (rowNumberTemporaryCustomer - rowNumberCustomer);
					nodeEdgeWeightMatrix[customer][temporaryCustomer] = numberHops;
				}
			}
		}
	}

	/**
	 * Check if the current node is customer node
	 * 
	 * @param nodeNumber
	 *            node number
	 * @return return true if node is customer node else false
	 */
	private boolean checkCustomerStartNode(int nodeNumber) {
		return ((nodeNumber >= 1 && nodeNumber <= 17));
	}

	/**
	 * @return the nodeMaxAmountMap
	 */
	public Map<String, Integer> getNodeMaxAmountMap() {
		return nodeMaxAmountMap;
	}

	/**
	 * @param nodeMaxAmountMap
	 *            the nodeMaxAmountMap to set
	 */
	public void setNodeMaxAmountMap(Map<String, Integer> nodeMaxAmountMap) {
		this.nodeMaxAmountMap = nodeMaxAmountMap;
	}
}
