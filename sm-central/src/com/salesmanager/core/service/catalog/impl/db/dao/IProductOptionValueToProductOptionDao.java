/*
 * Licensed to csti consulting 
 * You may obtain a copy of the License at
 *
 * http://www.csticonsulting.com
 * Copyright (c) 2006-Aug 24, 2010 Consultation CS-TI inc. 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.salesmanager.core.service.catalog.impl.db.dao;

import java.util.Collection;

import com.salesmanager.core.entity.catalog.ProductOptionValueToProductOption;

public interface IProductOptionValueToProductOptionDao {

	public void persist(ProductOptionValueToProductOption transientInstance);

	public void saveOrUpdate(ProductOptionValueToProductOption instance);

	public void delete(ProductOptionValueToProductOption persistentInstance);

	public ProductOptionValueToProductOption merge(
			ProductOptionValueToProductOption detachedInstance);

	public ProductOptionValueToProductOption findById(
			com.salesmanager.core.entity.catalog.ProductOptionValueToProductOptionId id);

	public Collection<ProductOptionValueToProductOption> findByIdProductOptionId(
			long productOptionId);

	public Collection<ProductOptionValueToProductOption> findByIdProductOptionValueId(
			long productOptionValueId);

	public void deleteAll(
			Collection<ProductOptionValueToProductOption> collection);

}