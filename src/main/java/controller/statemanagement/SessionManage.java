package controller.statemanagement;

public class SessionManage {

	public Boolean checkUser(String userSession){
    	if(userSession != null && !userSession.isEmpty()) return true;
    	else return false;
    }
}
