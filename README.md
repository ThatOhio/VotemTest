# VotemTest
Step 1) 
Install Java JRE with 'sudo apt-get install default-jre'

Step 2)
Installed Jenkins with 'sudo apt-get install jenkins'

Step 3) 
Finsh Jenkins Setup (involved viewing the logs to get a setup password). Did involve port forwarding 8080 on my machine over ssh. 

Step 4)
Configure Java and Maven in the Global Tool Configuration of jenkins 

Step 5)
Create test and github repository (alot happened here but I was toying around and didn't want to note all the steps as many were unnecessaru) 

Step 6)
Setup jenkins job to request, pulling code from the github repository (https://github.com/ThatOhio/VotemTest), building periodically (at 2 am), building using Maven, publishing JUnit results, and sending an email. 

Step 7) 
Trigger a build to see if it worked. 

Step 8) 
Forget to actually install Chrome, so lets do that with 
```java
wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
echo 'deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main' | sudo tee /etc/apt/sources.list.d/google-chrome.list
sudo apt-get update 
sudo apt-get install google-chrome-stable
```

Step 9) 
Build again, this time we get a pass. 

# The Test
After figuring out Java, JUnit, Maven, etc with some research I setup a project with a single test that scrapes the Blockchain Wikipedia article as spec'd.  
