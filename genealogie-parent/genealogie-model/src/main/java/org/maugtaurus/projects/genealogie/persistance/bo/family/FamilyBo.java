package org.maugtaurus.projects.genealogie.persistance.bo.family;

import org.maugtaurus.projects.genealogie.persistance.model.family.Family;

public interface FamilyBo {
	void save(Family family);
	void delete(Family family);
}
