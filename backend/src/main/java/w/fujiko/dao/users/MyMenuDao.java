package w.fujiko.dao.users;

import java.util.List;

import fte.api.universal.UniversalCrud;
import w.fujiko.model.masters.users.MyMenu;

public interface MyMenuDao extends UniversalCrud<MyMenu,Integer> {
	public List<MyMenu> getCustomizeMenuOfUser(Integer userid);
}