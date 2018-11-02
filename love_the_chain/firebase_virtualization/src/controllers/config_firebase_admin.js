
import admin from 'firebase-admin';

//var admin = require("firebase-admin");

var serviceAccount = {
  
  "type": "service_account",
  "project_id": "hackathon-bd330",
  "private_key_id": "1adb45100f17a7c783c1fa797b1e9a7cc1658a91",
  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC1PHnvJNgyJ1Zc\nph8EL135I+kHuIUx1K4cK4MX+xkzqWG0DcUD/iiK2RCzQMAyiG+2JQ210ydsomuz\nZqY36atHpaZ0kP0jhOWG8nYKbdaQ5p0hYwxlPutl+1ph3oh1rejE26KzXo9TTr06\nwmsHqwtVv6eUPtPg134u1xHVWHjzI9OdjLAMKeA+iyNB/TMrtdtH/n0eMuyYStz7\nn6vpTtKOQRPLTli1f3Jejkm8xD9cODHuoN4I76E/ON637yPrK3+TsTSfX2EoIFkH\nvsX8oWzxPFqH+s8bj++ftRsG6Zx3HyHjMcaXtyhe8mJgib6icUKXd8MMt7pBK27/\n1rU7yyHlAgMBAAECggEAVan1NI0kx2gCkR5QMMk20OkhX23fPzN47IXAnBgqzFl2\nfgW4Ju/+N8rr8t5bOkd1DhIrjdL1ooqO/6g4TttbAy2QJS7XB+/sEcIO+1T5g1oN\n0Vcg58Y6u+R3zyTBOWZvTIVIPSIn+seP9/MnZeN6IxjI2/Bqc69HzFoJ6t67pKhv\n4pGqt9z9/W7iLMBEcIKQ71N4WMcQDiz5e97UW+4C72APF1EQU3/FO3VUh5ATAorv\nILzdtLRFmbeGd7rJNkKttx9f4vSAllVkop5R0/TIjqgXF6o0RBUG9UXRA+PnmEN1\nnCvTWqNQdSbgQC93wk6A0hFOt4tAVaN3Qn4fcCuqDQKBgQD3t1+HJWMbjN3yhQIj\n2Otv5i6dvznAcWR1IfxZij+Qwjw+eEFMV8GCGk8Yl8aDkPdteWnJuMGfCplQ4V0x\nPaw3Z9gscRqFMwxKjl8XBEbbGDjyOzUkHxLqfNF4YIXsXusF1WGukOVLgqm4XNdV\nTI2jSK11HSRqxCO4Jcypv9TdzwKBgQC7S/ya8WgWLwug619LcoAYiuxLuqUJO9dc\nACWDliujK7fjyJ27h4h/B1fkzP0u809dXV9802YYXscjVdporooVWzvjYKcO5BQ9\nFYav0a45U4ZQeeq/+7WHwff0YqHF0/c0GPlk2uPaZNaykT+W1O62ODx3wx1EBuDq\nKhl/3dFGCwKBgQD1InvgK3lWDCKfGkyx2Lmz9F9xL4khDZVR7J3ipdKdl/kyAKLt\nv3H5I7veJGQIUm7n4Nx1COZiUoTB6yLj1ZoVAiiEYjDSW1/wb89d4ZeUvPWtkDXY\nVv/LGFXZ//rHnG6LSUvvCeg+v0Dg9s8I1EbdrNAqMvqUxSBr3MwZt4IZNQKBgCaF\nd1vKwNSnMmYTYtMappvlnrnoll7yztVeNlj8/tadfEG+Firmsj0mHn6xZnVMl4sl\nVClRNVNX0u9CjhwvobjIveWDEHjwkfUKQ1xP7NxfgGV23cBEnc5hJ4U87/bHJLWz\nWT/UAT7+tGWfmAuGmd4ibNVY7OD75TBkHkB9N7iHAoGAAceJFFZHVwzSC8enRXmZ\nbOXKvu73TSku74evHE99E5XQfHwLq8e8nw3OzI5BkQaDYeTFfAYeIbNcCAZl/6RS\nsJUrnfLJX6c7XnwTvKqEHXqX/B0jJOM51zarjpPhBPsJz/2diICjkm6RIV5gj9Zp\n9N6OV0GrvV7mp4qkTsVfxBQ=\n-----END PRIVATE KEY-----\n",
  "client_email": "firebase-adminsdk-pz6a9@hackathon-bd330.iam.gserviceaccount.com",
  "client_id": "100842351662241067919",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://oauth2.googleapis.com/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-pz6a9%40hackathon-bd330.iam.gserviceaccount.com"

}

admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://crash-test-2dd98.firebaseio.com"
});

export default admin;