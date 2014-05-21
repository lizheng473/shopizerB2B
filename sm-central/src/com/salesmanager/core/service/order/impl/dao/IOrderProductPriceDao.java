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
package com.salesmanager.core.service.order.impl.dao;

import java.util.Collection;

import com.salesmanager.core.entity.orders.OrderProductPrice;

public interface IOrderProductPriceDao {

	public void persist(OrderProductPrice transientInstance);

	public void saveOrUpdate(OrderProductPrice instance);

	public void saveOrUpdateAll(Collection<OrderProductPrice> coll);

	public void delete(OrderProductPrice persistentInstance);

	public void deleteAll(Collection<OrderProductPrice> coll);

	public OrderProductPrice findById(int id);

}