/*
 *  Licensed to GIScience Research Group, Heidelberg University (GIScience)
 *
 *   http://www.giscience.uni-hd.de
 *   http://www.heigit.org
 *
 *  under one or more contributor license agreements. See the NOTICE file 
 *  distributed with this work for additional information regarding copyright 
 *  ownership. The GIScience licenses this file to you under the Apache License, 
 *  Version 2.0 (the "License"); you may not use this file except in compliance 
 *  with the License. You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package heigit.ors.geocoding.geocoders;

public abstract class AbstractGeocoder implements Geocoder {

	protected String geocodingURL;
	protected String reverseGeocodingURL;
	protected String userAgent;
	
	public AbstractGeocoder(String geocodingURL, String reverseGeocodingURL, String userAgent)
	{
		this.geocodingURL = geocodingURL;
		this.reverseGeocodingURL = reverseGeocodingURL;
		this.userAgent = userAgent;
	}
	
	public abstract GeocodingResult[] geocode(String address, String languages, SearchBoundary searchBoundary, int limit) throws Exception;
	
	public abstract GeocodingResult[] geocode(Address address, String languages, SearchBoundary boundary, int limit) throws Exception;
	
	public abstract GeocodingResult[] reverseGeocode(double lon, double lat, int limit) throws Exception;
}