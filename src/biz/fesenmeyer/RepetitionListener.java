package biz.fesenmeyer;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class RepetitionListener implements UpdateListener {

	@Override
	public void update(EventBean[] arg0, EventBean[] arg1) {
		System.out.println(Main.getPerson()+" wird voraussichtlich noch weitere Diebstähle begehen");
	}

}
