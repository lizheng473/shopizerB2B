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
package com.salesmanager.core.service.system.impl.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.salesmanager.core.entity.system.CentralSequencer;

@Repository
public class SystemDao extends HibernateDaoSupport implements ISystemDao {

	private static final Log log = LogFactory.getLog(SystemDao.class);

	@Autowired
	public SystemDao(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.salesmanager.core.service.system.impl.dao.ISystemDao#
	 * incrementOrderIdCounter()
	 */
	public long incrementOrderIdCounter() throws RuntimeException {

		try {

			// Query q =
			// super.getSession().createQuery("from CentralSequencer c where c.centralSequencerId=:p").setMaxResults(1);
			// q.setParameter("p", 1);
			// q.setLockMode("c", LockMode.UPGRADE);

			CentralSequencer sequence = (CentralSequencer) super
					.getHibernateTemplate().get(CentralSequencer.class,
							new Integer(1), LockMode.UPGRADE);

			// CentralSequencer sequence =
			// (CentralSequencer)session.get(CentralSequencer.class, new
			// Integer(1), LockMode.UPGRADE);

			// CentralSequencer sequence = (CentralSequencer)q.uniqueResult();
			long currentCount = sequence.getOrderIdNextValue();
			long newCount = currentCount + 1;
			sequence.setOrderIdNextValue(newCount);
			// super.getHibernateTemplate().update(sequence);
			return newCount;

		} catch (RuntimeException e) {
			log.error(e);
			throw e;
		}

	}

	public void test() {

	}

}
