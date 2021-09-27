node{
  stage('SCM Checkout'){
    git 'https://github.com/abhisheknegi941/javaspringboot'
  }
  stage('Compile-Package'){
    // Get maven home path
    def mvnHome = tool name: 'maven-3', type: 'maven'
    bat "${mvnHome}/bin/mvn package"
  }
}
