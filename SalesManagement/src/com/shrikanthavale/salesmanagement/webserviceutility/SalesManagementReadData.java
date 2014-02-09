/**
 * 
 */
package com.shrikanthavale.salesmanagement.webserviceutility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestion;
import com.shrikanthavale.salesmanagement.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.salesmanagement.webserviceutility.RestClient.RequestMethod;

/**
 * @author Shrikant Havale
 * 
 */
public class SalesManagementReadData {

	// private static final String ACCESS_QUESTION_OPTIONS_URL =
	// "http://10.0.2.2:8080/SalesManagementWebservice/rest/salesmanagement/options";
	private static final String ACCESS_QUESTION_OPTIONS_URL = "http://tomcat7-shrikanthavale.rhcloud.com/SalesManagementWebservice/rest/salesmanagement/options";

	// private static final String ACCESS_QUESTION_URL =
	// "http://10.0.2.2:8080/SalesManagementWebservice/rest/salesmanagement/questions";
	private static final String ACCESS_QUESTION_URL = "http://tomcat7-shrikanthavale.rhcloud.com/SalesManagementWebservice/rest/salesmanagement/questions";

	private static String jsonQuestionOptions = null;

	private static String jsonQuestions = null;

	/**
	 * private constructor to avoid initialization from external
	 */
	private SalesManagementReadData() {
	}

	// get the data using web service
	public static Map<String, Integer> getMapNodeMaxAmount() throws Exception {

		Map<String, Integer> nodeMaxAmountMap = new HashMap<String, Integer>();
		RestClient restClient = new RestClient(ACCESS_QUESTION_OPTIONS_URL);
		try {

			if (jsonQuestionOptions == null) {
				jsonQuestionOptions = restClient.execute(RequestMethod.GET);
			}

			Gson gson = new Gson();
			SalesManagementQuestionOptions[] salesManagementQuestionOptionsArray = gson
					.fromJson(jsonQuestionOptions,
							SalesManagementQuestionOptions[].class);

			// create hash map for maximum out of the list obtained
			for (SalesManagementQuestionOptions temp : salesManagementQuestionOptionsArray) {
				if (nodeMaxAmountMap.get(temp.getCaseStudyNode()) != null
						&& nodeMaxAmountMap.get(temp.getCaseStudyNode()) < temp
								.getQuestionOptionMoneyAssoicated()) {
					nodeMaxAmountMap.put(temp.getCaseStudyNode(),
							temp.getQuestionOptionMoneyAssoicated());

				} else if (nodeMaxAmountMap.get(temp.getCaseStudyNode()) == null) {
					nodeMaxAmountMap.put(temp.getCaseStudyNode(),
							temp.getQuestionOptionMoneyAssoicated());
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return nodeMaxAmountMap;

	}

	public static SalesManagementQuestion getSalesManagementQuestionDetailsForCustomer(
			String customer) throws Exception {

		SalesManagementQuestion salesManagementQuestion = null;
		RestClient restClient = new RestClient(ACCESS_QUESTION_URL);
		try {

			if (jsonQuestions == null) {
				jsonQuestions = restClient.execute(RequestMethod.GET);
			}

			Gson gson = new Gson();
			SalesManagementQuestion[] salesManagementQuestionArray = gson
					.fromJson(jsonQuestions, SalesManagementQuestion[].class);

			// create hash map for maximum out of the list obtained
			for (SalesManagementQuestion temp : salesManagementQuestionArray) {
				if (temp.getCaseStudyNode().equalsIgnoreCase(customer)) {
					salesManagementQuestion = temp;
					break;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return salesManagementQuestion;
	}

	public static List<SalesManagementQuestion> getSalesManagementQuestionList()
			throws Exception {

		List<SalesManagementQuestion> salesManagementQuestions = new ArrayList<SalesManagementQuestion>();

		RestClient restClient = new RestClient(ACCESS_QUESTION_URL);
		try {

			if (jsonQuestions == null) {
				jsonQuestions = restClient.execute(RequestMethod.GET);
			}

			Gson gson = new Gson();

			SalesManagementQuestion[] salesManagementQuestionArray = gson
					.fromJson(jsonQuestions, SalesManagementQuestion[].class);

			salesManagementQuestions = Arrays
					.asList(salesManagementQuestionArray);

		} catch (Exception e) {
			throw e;
		}

		return salesManagementQuestions;

	}

	public static List<SalesManagementQuestionOptions> getSalesManagementQuestionOptionsMoneyEvaluation(
			String customer) throws Exception {

		List<SalesManagementQuestionOptions> salesManagementQuestionOptions = new ArrayList<SalesManagementQuestionOptions>();

		RestClient restClient = new RestClient(ACCESS_QUESTION_OPTIONS_URL);
		try {

			if (jsonQuestionOptions == null) {
				jsonQuestionOptions = restClient.execute(RequestMethod.GET);
			}

			Gson gson = new Gson();

			SalesManagementQuestionOptions[] salesManagementQuestionOptionsArray = gson
					.fromJson(jsonQuestionOptions,
							SalesManagementQuestionOptions[].class);

			// create hash map for maximum out of the list obtained
			for (SalesManagementQuestionOptions temp : salesManagementQuestionOptionsArray) {
				if (temp.getCaseStudyNode().equalsIgnoreCase(customer)) {
					salesManagementQuestionOptions.add(temp);
				}
			}

		} catch (Exception e) {
			throw e;
		}

		return salesManagementQuestionOptions;

	}

	/**
	 * @return the jsonQuestionOptions
	 */
	public static String getJsonQuestionOptions() {
		return jsonQuestionOptions;
	}

	/**
	 * @param jsonQuestionOptions
	 *            the jsonQuestionOptions to set
	 */
	public static void setJsonQuestionOptions(String jsonQuestionOptions) {
		SalesManagementReadData.jsonQuestionOptions = jsonQuestionOptions;
	}

	/**
	 * @return the jsonQuestions
	 */
	public static String getJsonQuestions() {
		return jsonQuestions;
	}

	/**
	 * @param jsonQuestions
	 *            the jsonQuestions to set
	 */
	public static void setJsonQuestions(String jsonQuestions) {
		SalesManagementReadData.jsonQuestions = jsonQuestions;
	}
}
