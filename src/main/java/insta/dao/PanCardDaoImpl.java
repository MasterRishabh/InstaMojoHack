package insta.dao;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import entities.Pan;
import entities.PanDetail;
import entities.UserDetails;
import object.PanStruct;

@Transactional
@Repository
public class PanCardDaoImpl implements PanCardDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<UserDetails> getLeaderBoard() {
		
		String hql = "from UserDetails as ud order by ud.reviewPoints desc" ;
		return entityManager.createQuery(hql).getResultList();
	}


	@Override
	public void savePancard(PanStruct panStruct, byte[] file) {
		PanDetail panDetail = new PanDetail();
		panDetail.setPanId(panStruct.getPanId());
		panDetail.setPanUserName(panStruct.getUserName());
		panDetail.setDateOfBirth(new Date(panStruct.getDateOfBirth()));
		panDetail.setPanimage(file);
		UserDetails userdetail = entityManager.find(UserDetails.class, "1");
		userdetail.setLoginid("1");
		panDetail.setUserdetail(userdetail);
		
		entityManager.persist(panDetail);
		
	}


	@Override
	public List<PanDetail> getAllUnverifiedPan() {
		String hql = "from PanDetail as pd where pd.userdetail is null";
		return entityManager.createQuery(hql).getResultList();
	}
}
