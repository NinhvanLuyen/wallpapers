pipeline {
  agent any
  stages {
    stage('pull code') {
      steps {
        git(url: 'https://github.com/NinhvanLuyen/wallpapers.git', branch: 'master', changelog: true)
      }
    }

  }
}