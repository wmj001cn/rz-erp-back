
const axios = require('axios').default;

const port = 8081


const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser');
const app = express()

app.use(cors());
app.use(bodyParser.json());
app.use(function(req, res, next) {
  res.header("Content-Security-Policy", "default-src 'self';script-src 'self';object-src 'none';img-src 'self';media-src 'self';frame-src 'none';font-src 'self' data:;connect-src 'self';style-src 'self'");
  return next();
});
app.use(express.static('static'))

app.all('/upload', (req, res) => {
   let baseUrl = "localhost:9200";
    var entry = req.body
    var location = entry.location
    console.log(req.body);
    axios.post(`http://${baseUrl}/business/_doc`, {
      "name": entry.name,
      "bizz": entry.business,
      "address": entry.address,
      "call_number": entry.callNumber,
      "wechatId": entry.wechatId,
      "location": {
          "lat": location.lat,
          "lon": location.lon
      }
  }).then(resp => {
        console.log(resp.data);
        res.json(resp.data)

    });
});
app.all('/search', (req, res) => {
    console.log(req.body);
    let body = req.body;
    let lat = body.lat;
    let lon = body.lon;

    let baseUrl = "localhost:9200";
    var url = `http://${baseUrl}/business/_search`;
        axios
          .post(`${url}`, {
            query: {
              bool: {
                must: {
                  query_string: { query: body.query }
                },

                filter: {
                  geo_distance: {
                    distance: "1km",
                    location: {
                      lat: lat,
                      lon: lon
                    }
                  }
                }
              }
            },

            stored_fields: ["_source"],
            script_fields: {
              distance: {
                script: {
                  lang: "painless",

                  source: `doc['location'].arcDistance(${lat},${lon})`
                }
              }
            },
            sort: {
              _geo_distance: {
                location: {
                  lat: lat,
                  lon: lon
                },
                order: "asc",
                unit: "km"
              }
            }
          })
          .then(resp => {
            //console.log(resp);
            res.status(200).json(resp.data);
          });
      

    
})

app.listen(port, () => console.log(`Example app listening on port ${port}!`))