package insta.dao;

import java.util.List;

import entities.Pan;
import entities.PanDetail;
import entities.UserDetails;
import object.PanStruct;

public interface PanCardDao {

	public List<UserDetails> getLeaderBoard();
	void savePancard(PanStruct panStruct, byte[] bs);
	public List<PanDetail> getAllUnverifiedPan();

}
