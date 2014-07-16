package at.fhooe.mcm.it.adid.model.utility;


/**
 * This is used to communicates the fragment with other activity, which in turns
 * communicates to other fragments
 * 
 * @author Shrikant Havale
 * 
 */
public interface FragmentCommunicationInterface {

	/**
	 * This will notify other fragments to update their image views if the answer is correct.
	 */
	public void notifyOtherFragmentsUpdateImageView(int imageViewNumber);
	

	/**
	 * This will notify Description fragments to update its image views when
	 * all answers are answered correctly.
	 */
	public void notifyDesciptionFragmentsUpdateImageView();	
	
}
