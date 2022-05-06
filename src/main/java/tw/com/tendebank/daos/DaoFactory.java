package tw.com.tendebank.daos;

/**
 * DAO工廠
 * @author Edison
 *
 */
public class DaoFactory {

	private HProductDAO hProductDao;
	
	private HMemberDAO hMemberDao;
	
	private HManagerDAO hManagerDao;
	
	private HVendorDAO hVendorDao;
	
	private HQAndADAO hQAndADao;
	
	private HOrdersDAO hOrdersDao;
	
	private HStreetNameDAO hStreetNameDao;
	
	private MProductDAO mProductDao;

	public HProductDAO gethProductDao() {
		return hProductDao;
	}

	public void sethProductDao(HProductDAO hProductDao) {
		this.hProductDao = hProductDao;
	}

	public HMemberDAO gethMemberDao() {
		return hMemberDao;
	}

	public void sethMemberDao(HMemberDAO hMemberDao) {
		this.hMemberDao = hMemberDao;
	}

	public HManagerDAO gethManagerDao() {
		return hManagerDao;
	}

	public void sethManagerDao(HManagerDAO hManagerDao) {
		this.hManagerDao = hManagerDao;
	}

	public HVendorDAO gethVendorDao() {
		return hVendorDao;
	}

	public void sethVendorDao(HVendorDAO hVendorDao) {
		this.hVendorDao = hVendorDao;
	}

	public HQAndADAO gethQAndADao() {
		return hQAndADao;
	}

	public void sethQAndADao(HQAndADAO hQAndADao) {
		this.hQAndADao = hQAndADao;
	}

	public HOrdersDAO gethOrdersDao() {
		return hOrdersDao;
	}

	public void sethOrdersDao(HOrdersDAO hOrdersDao) {
		this.hOrdersDao = hOrdersDao;
	}

	public HStreetNameDAO gethStreetNameDao() {
		return hStreetNameDao;
	}

	public void sethStreetNameDao(HStreetNameDAO hStreetNameDao) {
		this.hStreetNameDao = hStreetNameDao;
	}

	public MProductDAO getmProductDao() {
		return mProductDao;
	}

	public void setmProductDao(MProductDAO mProductDao) {
		this.mProductDao = mProductDao;
	}
}
