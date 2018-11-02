import express from 'express';
import routes from './src/routes/routes';
import bodyParser from 'body-parser'; 
import cors from 'cors';

const app = express();
const PORT = 3031;

app.use( bodyParser() );
app.use(cors());

routes(app);
cors({credentials: true, origin: true})

/*
app.get('/', (req, res) => {
    res.send(`Node and express server is running on ${PORT} `);
});
*/

app.listen(PORT, () =>{
    console.log(`Your server is running on port ${PORT}`);
});