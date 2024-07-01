# Frontend Application Source Code 
This project is written in React and was bootstrapped using the create-react-app script. 

## Steps to Get Started
### Dependencies
- NodeJS Installation
- Cloned project repository
- Hosting service to deploy application in production (we used Digital Ocean, contact us for more details for application deployments)

### Development and Production Deployments
In the project directory after cloning, run:
```shell
npm install
```
Then, create a .env, .env.production, and .env.development file. In .env.development and .env, point localhost to a variable named REACT_APP_HOST_IP and point the IP address where your REST API server is running to the same variable in .env.production.

You can then create a build using the available scripts, and the build wrapper should bootstrap the correct address based off your .env.production file. Using development scripts, your application should use localhost. 

## Available Scripts
In the project directory, you can run:

### `npm start`
Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm run build`
Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\

## Learn More
To learn React, check out the [React documentation](https://reactjs.org/).



