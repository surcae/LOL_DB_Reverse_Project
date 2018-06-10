package Background;

import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;

public class APIManager {
	// ΩÃ±€≈Ê
		private static APIManager apiManager = null;
		private APIManager() {
		}
		
		public static APIManager getAPIManager() {
			if(apiManager==null) {
				apiManager = new APIManager();
			}
			return apiManager;
		}
		ApiConfig config = new ApiConfig().setKey("RGAPI-3a5d08be-383a-4808-b6ed-60d4b9755c3f");
		RiotApi api = new RiotApi(config);
		Summoner summoner;
		public RiotApi getApi() {
			return api;
		}

		public void setApi(RiotApi api) {
			this.api = api;
		}

		public Summoner getSummoner() {
			return summoner;
		}

		public void setSummoner(Summoner summoner) {
			this.summoner = summoner;
		}
		
		
}
