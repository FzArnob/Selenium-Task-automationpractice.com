# automationTask2022
## Installing

Clone this project and name it accordingly:

``git clone https://github.com/FzArnob/automationTask2022.git``

# Getting Started
- You can directly import the project in Eclipse.

> 1. Open File->Import
> 2. Select "Existing Projects into Workspace" from the Selection Wizard
> 3. Select Next to get the Import Wizzard. Browse to find the location of the Project
> 4. Make sure the Project you want is checked, then hit Finish.``

- Add all jars to package ClassPath from /Lib/
- Select Driver Browser < Match driver and Installed versions >
> ``// Compatibility testing with different browsers (Select one for test)``
> - ``	  	driver = LoadSiteInChrome();\n``
> - ``//		driver = LoadSiteInFirefox();\n``
> - ``//		driver = LoadSiteInOpera();``
- Change dataset for each new test

# Languages used:
- Java

# Conclusion
- Full [Reports](/reports/) and [Results](/results/) are given for sample data
- One Major bug caught by the automation test
> Feature: Add Color Filter
> Description: From Catalog, color filter doesn't work at the first attempt click by the user on Text.
> Manually Tested: Yes, takes two attempts to add a color filter by Text
> Comment: Color Box works fine.
