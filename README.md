# Correvate Test

1. Create a SpringBoot app with an endpoint to receive a list of files (form-data) and
   return a zipped file containing the input files.
2. Package the app in a Docker image and provide the instructions to run.


## Instructions
1. Download the docker image `docker pull priz/zipdemo`
2. Run with `docker run -p 8080:8080 priz/zipdemo`

The exposed endpoint is /zip-files (`localhost:8080/zip-files`)
It expects a form-data with an array of files in a @RequestBody with key `files`