package com.jira.api.pojo.build;

import com.jira.api.pojo.dto.CreateFilterRequestData;

public class BuildCreateFilterPayload {

    public static CreateFilterRequestData setCreateFilterRequestData()
    {
        CreateFilterRequestData createFilterRequestData = new CreateFilterRequestData();
        createFilterRequestData.setName("Display all open bugs");
        createFilterRequestData.setDescription("Lists all open bugs");
        createFilterRequestData.setJql("type = Bug and resolution is empty");
        createFilterRequestData.setFavourite(true);
        createFilterRequestData.setEditable(false);

        return createFilterRequestData;
    }

}
