package org.maugtaurus.projects.genealogie.persistance.dao.family;

import org.maugtaurus.projects.genealogie.persistance.model.family.Family;

public interface FamilyDao {
	void save(Family family);
	void delete(Family family);
}
