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

import com.salesmanager.core.entity.orders.OrderProductDownload;

public interface IOrderProductDownloadDao {

	public void persist(OrderProductDownload transientInstance);

	public void saveOrUpdate(OrderProductDownload instance);

	public void delete(OrderProductDownload persistentInstance);

	public OrderProductDownload merge(OrderProductDownload detachedInstance);

	public OrderProductDownload findById(long id);

	public void saveOrUpdateAll(Collection<OrderProductDownload> coll);

	public Collection<OrderProductDownload> findByOrderId(long id);

	public void deleteAll(Collection<OrderProductDownload> coll);

}