/*******************************************************************************
 * Copyright (C) 2021 Dapeng Dong
 *  
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 ******************************************************************************/
package org.dapeng.usicms.handler;

import java.util.ArrayList;
import java.util.List;

import org.dapeng.usicms.model.UserStory;

public class ProjectLevelConfigs {
	// about class projetLevelconfigs
	private static ProjectLevelConfigs projectLevelConfigs;

	public String projectName = "ProjectLevelConfigs";
	public List<UserStory> userStories = new ArrayList<>();

	// an array list with class type userstory called userstories
	public String FIELD_SPLITTER = "&&";
	public String RECORD_SPLITTER = "/n&-----------&/n";

	private ProjectLevelConfigs() {
	}

	synchronized public static ProjectLevelConfigs getProjectLevelConfigs() {
		if (projectLevelConfigs == null) {
			projectLevelConfigs = new ProjectLevelConfigs();
		}
		return projectLevelConfigs;
	}

	synchronized public void UpdateUserStoryStatus(String idName, String newStatus) {
		String id = idName.split("--")[0];// after spilt, it is an array, so we get [0] for id
		for (UserStory us : userStories) {
			if (us.getId().equalsIgnoreCase(id)) {
				us.setStatus(newStatus);
			}
		}
	}
}
