package com.team.app.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.app.constant.AppConstants;
import com.team.app.dao.AreaDao;
import com.team.app.dao.LandmarkDao;
import com.team.app.dao.PlaceDao;
import com.team.app.domain.Area;
import com.team.app.domain.Landmark;
import com.team.app.domain.Place;
import com.team.app.dto.OrganisationDto;
import com.team.app.logger.AtLogger;
import com.team.app.service.APLService;

@Service
public class APLServiceImpl implements APLService {
	
	private static final AtLogger logger = AtLogger.getLogger(APLServiceImpl.class);

	@Autowired 
	private AreaDao areaDao;
	
	@Autowired 
	private LandmarkDao landmarkDao;
	
	@Autowired 
	private PlaceDao placeDao;
	
	public List<OrganisationDto> getOrganisation() throws Exception {
		List<OrganisationDto>  list=null;
		try {
		   	String url=AppConstants.org_url;
			logger.debug("URLConn",url);
			URL obj1 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj1.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Grpc-Metadata-Authorization",AppConstants.jwtToken);
			
			    
			int responseCode = con.getResponseCode();
				logger.debug("POST Response Code :: " + responseCode);
					    				
			if(responseCode == HttpURLConnection.HTTP_OK) {
				logger.debug("Token valid,POST Response with 200");
				
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				in.close();
				
				JSONObject json=null;
					json=new JSONObject();
				json=(JSONObject)new JSONParser().parse(response.toString());
			
				JSONArray arr=(JSONArray) json.get("result");
				
				list=new ArrayList<OrganisationDto>();
				
				if(arr!=null && arr.size()>0){
					OrganisationDto dto=null;
					 for (int i = 0; i < arr.size(); i++) {
						 JSONObject jsonObj = (JSONObject) arr.get(i);
						
						if(jsonObj.get("name").toString().equalsIgnoreCase(AppConstants.Organisation)){
							logger.debug("Name matching ..");
							logger.debug("Organisation name ..",jsonObj.get("name").toString());
							logger.debug("Organisation id ..",jsonObj.get("id").toString());
							dto=new OrganisationDto();
							dto.setOrgName(jsonObj.get("name").toString());
							dto.setOrgId(jsonObj.get("id").toString());
							list.add(dto);
																				
						}
					 }
		        }
			}
			
	   }catch(Exception e){
			e.printStackTrace();
	   }
		
		
		return list;
	}


	public List<Area> getAreasByOrgId(String orgId) throws Exception {
		
		return areaDao.getAreasByOrgId(orgId);
	}


	
	public Place getPlaceById(String placeforLandmark) throws Exception {
		return placeDao.getPlaceById(placeforLandmark);
	}


	
	public List<Landmark> getSpecificLandmarks(String area, String place, String orgId) throws Exception {
		
		if(area != null){
			return landmarkDao.getSpecificLandmarksByAreaId(area);
		}else if (place != null){
			return landmarkDao.getSpecificLandmarksByPlaceId(place);
		}else if (orgId != null){
			return landmarkDao.getSpecificLandmarksByOrgId(orgId);
		}
		return null;
	}


	public Area insertArea(Area areaObj) throws Exception {
		Area a=null;
				a=areaDao.getAreaByNameAndOrgId(areaObj.getAreaname(),areaObj.getOrgId());
		if(a!=null){
			return null;			
		}else{
			return areaDao.save(areaObj);
		}
				
	}


	
	public Area getAreasByAreaId(String areaId) throws Exception {
	
		return areaDao.getAreasByAreaId(areaId);
	}


	
	public Area getAreaByOrgAndAreaId(String orgId, String areaId) {
			return areaDao.getAreaByOrgAndAreaId(orgId,areaId);
	}


	
	public Area updateArea(Area area) throws Exception {
		Area a=areaDao.checkAreaExistence(area.getAreaname(), area.getOrgId());
		if(a!=null){
			return null;
		}else{
			return areaDao.save(area);	
		}
		
	}


	
	public Place insertPlace(Place place, String areaId) throws Exception {
		Place p=placeDao.getPlaceByAreaAndName(place.getPlacename(),areaId);
		if(p!=null){
			return null;
		}else{
			return placeDao.save(place);
		}
		
	}


	public Place getPlaceByPlaceAndAreaId(String placeId, String areaId) throws Exception {
		return placeDao.getPlaceByPlaceAndAreaId(placeId,areaId);
	}


	
	public Place updatePlace(Place place) throws Exception {
		return placeDao.save(place);
	}


	
	public Landmark insertLandmark(Landmark landmark, String placeId) throws Exception {
		Landmark l=landmarkDao.getLandmarkByNameAndPlaceId(landmark.getLandmarkname(),placeId);
		if(l!=null){
			return null;
		}else{
			return landmarkDao.save(landmark);
		}
		
	}


	
	public Landmark getLandMarkByIdAndPlaceId(String landmarkId, String placeId) throws Exception {
		return landmarkDao.getLandMarkByIdAndPlaceId(landmarkId,placeId);
	}


	
	public Landmark updateLandmark(Landmark lm) throws Exception {
		return landmarkDao.save(lm);
	}


	public List<Area> getAreasListByAreaId(String areaId) throws Exception {
		return areaDao.getAreasListByAreaId(areaId);
	}


	
	public Landmark getLandMarkById(String landmarkId) throws Exception {
		return landmarkDao.getLandMarkById(landmarkId);
	}


	public List<Object[]> getLandMarkByAPL(String orgId,String landMarkText) throws Exception {
					
		return landmarkDao.getLandMarkByAPL(orgId,landMarkText);
	}


	public List<Landmark> getLandMarkByUserLandmarkId(String landmarkId) throws Exception {
		
		return landmarkDao.getLandMarkByUserLandmarkId(landmarkId);
	}


	
	

	
	


	
	
	
	

}
