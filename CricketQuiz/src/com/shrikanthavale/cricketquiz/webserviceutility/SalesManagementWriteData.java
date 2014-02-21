/**
 * 
 */
package com.shrikanthavale.cricketquiz.webserviceutility;

import java.util.List;

import com.google.gson.Gson;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestion;
import com.shrikanthavale.cricketquiz.entities.SalesManagementQuestionOptions;
import com.shrikanthavale.cricketquiz.webserviceutility.RestClient.RequestMethod;

/**
 * @author Shrikant Havale
 * 
 */
public class SalesManagementWriteData {

	// private static final String ACCESS_QUESTION_OPTIONS_URL =
	// "http://10.0.2.2:8080/SalesManagementWebservice/rest/salesmanagement/writeoptions";
	private static final String ACCESS_QUESTION_OPTIONS_URL = "http://tomcat7-shrikanthavale.rhcloud.com/CricketQuizWebservice/rest/salesmanagement/writeoptions";

	// private static final String ACCESS_QUESTION_URL =
	// "http://10.0.2.2:8080/SalesManagementWebservice/rest/salesmanagement/writequestion";
	private static final String ACCESS_QUESTION_URL = "http://tomcat7-shrikanthavale.rhcloud.com/CricketQuizWebservice/rest/salesmanagement/writequestion";

	/**
	 * private constructor to avoid initialization from external
	 */
	private SalesManagementWriteData() {
	}

	/**
	 * This method accepts the entity of question and saves it to database using
	 * web service
	 * 
	 * @param salesManagementQuestion
	 *            Question entity containing all the details
	 * 
	 * @throws Exception
	 *             Exception if any
	 */
	public static Boolean saveNodeQuestionDetails(
			SalesManagementQuestion salesManagementQuestion) throws Exception {

		// convert the object to GSON String
		Gson gson = new Gson();
		String gsonString = gson.toJson(salesManagementQuestion);

		// create the rest client
		RestClient restClient = new RestClient(ACCESS_QUESTION_URL);

		// add the data as parameters
		restClient.AddParam("SalesManagementQuestion", gsonString);

		// post the data
		try {
			// save the updated data
			restClient.execute(RequestMethod.POST);

			// if the save was successfully, current cached data is no longer
			// good
			SalesManagementReadData.setJsonQuestions(null);

		} catch (Exception e) {
			throw e;
		}

		return true;
	}

	public static Boolean saveNodeQuestionOptions(
			List<SalesManagementQuestionOptions> salesManagementQuestionOptions)
			throws Exception {

		// convert the object to GSON String
		Gson gson = new Gson();
		String gsonString = gson.toJson(salesManagementQuestionOptions);

		// create the rest client
		RestClient restClient = new RestClient(ACCESS_QUESTION_OPTIONS_URL);

		// add the data as parameters
		restClient.AddParam("SalesManagementOptionList", gsonString);

		// post the data
		try {

			// save the data in database using webservice
			restClient.execute(RequestMethod.POST);

			// if the save was successfully, current cached data is no longer
			// good
			SalesManagementReadData.setJsonQuestionOptions(null);

		} catch (Exception e) {
			throw e;
		}

		return true;

	}
}