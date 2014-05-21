/*
 * Provided by CSTI Consulting 
 * Following GNU LESSER GENERAL PUBLIC LICENSE
 * You may obtain more details at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.central.shipping;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.salesmanager.central.profile.Context;
import com.salesmanager.central.profile.ProfileConstants;
import com.salesmanager.central.util.ValidationException;
import com.salesmanager.core.constants.ShippingConstants;
import com.salesmanager.core.entity.merchant.MerchantConfiguration;
import com.salesmanager.core.entity.reference.ModuleConfiguration;
import com.salesmanager.core.service.ServiceFactory;
import com.salesmanager.core.service.common.model.IntegrationKeys;
import com.salesmanager.core.service.common.model.IntegrationProperties;
import com.salesmanager.core.service.merchant.ConfigurationResponse;
import com.salesmanager.core.service.merchant.MerchantService;
import com.salesmanager.core.service.reference.ReferenceService;
import com.salesmanager.core.util.ShippingUtil;
import com.salesmanager.core.util.StringUtil;

//@Validation
public class ShippingupsxmlAction extends ShippingModuleAction {

	// user selections submited from the page
	private List globalServicesSelection = null;
	private String packageSelection = null;
	private IntegrationKeys keys;
	private IntegrationProperties properties;

	private final static String moduleid = "upsxml";

	private ConfigurationResponse configurations;

	private Map<String, String> globalServicesMap;// available services options
	private Map<String, String> packageMap;// available packages options options

	private Logger log = Logger.getLogger(ShippingupsxmlAction.class);

	public void prepareModule() throws Exception {

		Context ctx = (Context) super.getServletRequest().getSession()
				.getAttribute(ProfileConstants.context);
		Integer merchantid = ctx.getMerchantid();

		ReferenceService rservice = (ReferenceService) ServiceFactory
				.getService(ServiceFactory.ReferenceService);

		Locale locale = getLocale();

		ModuleConfiguration serviceconfig = rservice.getModuleConfiguration(
				moduleid, "service", "XX");


		if (serviceconfig == null) {
			throw new Exception("ModuleConfiguration does not exist for "
					+ moduleid + "-service-XX");
		}

		// parse services ONLY GLOBAL SERVICES

		Map servicemap = ShippingUtil.buildServiceMap(moduleid, locale);
		if (servicemap != null) {
			setGlobalServicesMap(servicemap);
		}
		// }

		// parse packages

		Map packages = ShippingUtil.buildPackageMap(moduleid, locale);
		if (packages != null) {
			setPackageMap(packages);
		}
		// }

		// get merchant configs

		MerchantService mservice = (MerchantService) ServiceFactory
				.getService(ServiceFactory.MerchantService);

		ConfigurationResponse config = mservice.getConfigurationByModule(
				moduleid, merchantid);
		
		this.setConfigurations(config);

	}

	public void displayModule() throws Exception {

		if (configurations != null) {
			IntegrationKeys keys = (IntegrationKeys) configurations
					.getConfiguration("upsxml-keys");
			setKeys(keys);

			IntegrationProperties props = (IntegrationProperties) configurations
					.getConfiguration("upsxml-properties");
			setProperties(props);

			// choosen package [1 package allowed]
			String packageoption = (String) configurations
					.getConfiguration("package-upsxml");
			if (!StringUtils.isBlank(packageoption)) {
				setPackageSelection(packageoption);
			} else {// default value
				setPackageSelection("02");
			}

			// selected services
			Map selectedservices = (Map) configurations
					.getConfiguration("service-global-upsxml");
			
			// no distinction between domestic and intl

			if (selectedservices != null) {
				Iterator i = selectedservices.keySet().iterator();
				List slist = new ArrayList();
				while (i.hasNext()) {
					String key = (String) i.next();
					slist.add(key);
				}
				setGlobalServicesSelection(slist);
			}

		}

	}

	/**
	 * Those validations are disabled
	 */
	/**
	 * @Validations( requiredFields = {
	 * @RequiredFieldValidator(message = "", key = "errors.required.upskey",
	 *                                 fieldName = "keys.key1"),
	 * @RequiredFieldValidator(message = "", key = "errors.required.userid",
	 *                                 fieldName = "keys.userId"),
	 * @RequiredFieldValidator(message = "", key =
	 *                                 "errors.required.upspassword", fieldName
	 *                                 = "keys.errors.required.upspassword") } )
	 **/

	public void saveModule() throws Exception {

		boolean fielderror = false;
		if (this.getKeys() == null
				|| StringUtils.isBlank(this.getKeys().getKey1())) {
			addFieldError("keys.key1", getText("errors.required.upskey"));
			fielderror = true;
		}
		if (StringUtils.isBlank(this.getKeys().getUserid())) {
			addFieldError("keys.userid", getText("errors.required.userid"));
			fielderror = true;
		}
		if (StringUtils.isBlank(this.getKeys().getPassword())) {
			addFieldError("keys.password", getText("errors.required.password"));
			fielderror = true;
		}

		if (this.getGlobalServicesSelection() == null
				|| this.getGlobalServicesSelection().size() == 0
				|| this.getGlobalServicesSelection().size() > 3) {
			addFieldError("globalServicesSelection",
					getText("message.error.maxglobalshipping"));
			fielderror = true;
		}

		if (StringUtils.isBlank(this.getPackageSelection())) {
			addFieldError("packageSelection",
					getText("message.error.packageoption"));
			fielderror = true;
		}

		if (fielderror) {
			throw new ValidationException("Missing fields");
		}

		Date date = new Date(new Date().getTime());

		List modulestosave = new ArrayList();

		Context ctx = (Context) super.getServletRequest().getSession()
				.getAttribute(ProfileConstants.context);
		Integer merchantid = ctx.getMerchantid();

		String submitedcredentials = ShippingUtil.buildShippingKeyLine(keys);
		String submitedproperties = ShippingUtil
				.buildShippingPropertiesLine(this.getProperties());

		String serviceline = StringUtil
				.buildMultipleValueLine(getGlobalServicesSelection());
		String packageOption = getPackageSelection();
		// first get the entry
		if (configurations != null) {
			// get credentials
			MerchantConfiguration credentials = configurations
					.getMerchantConfiguration(moduleid,
							ShippingConstants.MODULE_SHIPPING_RT_CRED);
			if (credentials != null) {
				credentials.setConfigurationValue1(submitedcredentials);
				credentials.setConfigurationValue2(submitedproperties);
			} else {
				credentials = new MerchantConfiguration();
				credentials
						.setConfigurationKey(ShippingConstants.MODULE_SHIPPING_RT_CRED);
				credentials.setConfigurationModule(moduleid);
				credentials.setDateAdded(date);
				credentials.setMerchantId(merchantid);
				credentials.setConfigurationValue1(submitedcredentials);
				credentials.setConfigurationValue2(submitedproperties);
			}
			credentials.setLastModified(date);
			modulestosave.add(credentials);

			// get packages
			MerchantConfiguration pack = configurations
					.getMerchantConfiguration(moduleid,
							ShippingConstants.MODULE_SHIPPING_RT_PKG_DOM_INT);
			if (pack != null) {
				pack.setConfigurationValue(getPackageSelection());
				pack.setConfigurationValue2(serviceline);
			} else {
				pack = new MerchantConfiguration();
				pack
						.setConfigurationKey(ShippingConstants.MODULE_SHIPPING_RT_PKG_DOM_INT);
				pack.setConfigurationModule(moduleid);
				pack.setDateAdded(date);
				pack.setConfigurationValue(packageOption);
				pack.setMerchantId(merchantid);
				pack.setConfigurationValue2(serviceline);
			}
			pack.setLastModified(date);
			modulestosave.add(pack);

		} else {// create both entries
			MerchantConfiguration credentials = new MerchantConfiguration();
			credentials
					.setConfigurationKey(ShippingConstants.MODULE_SHIPPING_RT_CRED);
			credentials.setConfigurationModule(moduleid);
			credentials.setDateAdded(date);
			credentials.setMerchantId(merchantid);
			credentials.setLastModified(date);
			credentials.setConfigurationValue1(submitedcredentials);
			credentials.setConfigurationValue2(submitedproperties);
			modulestosave.add(credentials);

			MerchantConfiguration pack = new MerchantConfiguration();
			pack
					.setConfigurationKey(ShippingConstants.MODULE_SHIPPING_RT_PKG_DOM_INT);
			pack.setConfigurationModule(moduleid);
			pack.setDateAdded(date);
			pack.setLastModified(date);
			pack.setMerchantId(merchantid);
			pack.setConfigurationValue(packageOption);
			pack.setConfigurationValue2(serviceline);
			modulestosave.add(pack);
		}

		MerchantService mservice = (MerchantService) ServiceFactory
				.getService(ServiceFactory.MerchantService);
		mservice.saveOrUpdateMerchantConfigurations(modulestosave);



	}

	public void deleteModule() throws Exception {

		Context ctx = (Context) super.getServletRequest().getSession()
				.getAttribute(ProfileConstants.context);
		Integer merchantid = ctx.getMerchantid();

		MerchantService mservice = (MerchantService) ServiceFactory
				.getService(ServiceFactory.MerchantService);
		mservice.cleanConfigurationLikeKeyModule("SHP_RT_", moduleid,
				merchantid);

	}

	public IntegrationKeys getKeys() {
		return keys;
	}

	public void setKeys(IntegrationKeys keys) {
		this.keys = keys;
	}

	public ConfigurationResponse getConfigurations() {
		return configurations;
	}

	public void setConfigurations(ConfigurationResponse configurations) {
		this.configurations = configurations;
	}

	public Map<String, String> getPackageMap() {
		return packageMap;
	}

	public void setPackageMap(Map<String, String> packageMap) {
		this.packageMap = packageMap;
	}

	public Map<String, String> getGlobalServicesMap() {
		return globalServicesMap;
	}

	public void setGlobalServicesMap(Map<String, String> globalServicesMap) {
		this.globalServicesMap = globalServicesMap;
	}

	public String getPackageSelection() {
		return packageSelection;
	}

	public void setPackageSelection(String packageSelection) {
		this.packageSelection = packageSelection;
	}

	public List getGlobalServicesSelection() {
		return globalServicesSelection;
	}

	public void setGlobalServicesSelection(List globalServicesSelection) {
		this.globalServicesSelection = globalServicesSelection;
	}

	public IntegrationProperties getProperties() {
		return properties;
	}

	public void setProperties(IntegrationProperties properties) {
		this.properties = properties;
	}

}
