package ro.trellteam.organisation.exceptions;

import java.util.HashMap;

/**
 * Class which contains every error code and it's description.
 */
public class ErrorHelper {

    private static HashMap<String, String> errorList;

    static {
        errorList = new HashMap<>();

        errorList.put("ORG_ERR_0", "An error has been encountered.");
        errorList.put("ORG_ERR_1", "Organisation was not found.");
        errorList.put("ORG_ERR_2", "Department was not found.");
        errorList.put("ORG_ERR_3", "Employee was not found.");
        errorList.put("ORG_ERR_4", "Required request input is missing or invalid.");
        errorList.put("ORG_ERR_5", "Required request body parameter is missing or invalid.");
        errorList.put("ORG_ERR_6", "An error was encountered while trying to create a department.");
        errorList.put("ORG_ERR_7", "An error was encountered while trying to delete a department.");
        errorList.put("ORG_ERR_8", "An error was encountered while trying to update a department.");
        errorList.put("ORG_ERR_9", "An error was encountered while trying to unassign an employee from a department.");
        errorList.put("ORG_ERR_10", "An error was encountered while trying to assign an employee to an department.");
        errorList.put("ORG_ERR_11", "An error was encountered while trying to create an employee.");
        errorList.put("ORG_ERR_12", "An error was encountered while trying to create an organisation.");
    }
}
