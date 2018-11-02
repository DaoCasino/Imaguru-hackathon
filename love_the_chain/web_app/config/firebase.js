import firebase from "firebase";

const config = {
  apiKey: "AIzaSyBUh2hKv0MeOE0mGFNV5_h9Ys1gULBZMe8",
  authDomain: "hackathon-bd330.firebaseapp.com",
  databaseURL: "https://hackathon-bd330.firebaseio.com",
  projectId: "hackathon-bd330",
  storageBucket: "hackathon-bd330.appspot.com",
  messagingSenderId: "352791383967"
};


if (!firebase.apps.length) {
  try{

    firebase.initializeApp(config);
  }
  catch (err) {
    console.log(err.Message);
  }

}




export default firebase;