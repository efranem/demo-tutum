import { Stack } from "@mui/material";
import { Button } from "primereact/button";
import Card from "@mui/material/Card";
import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { ThemeProvider, createTheme } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import Container from "@mui/material/Container";
import { APIfetchApi } from "./../API/APIfetch";
import backgroundImage from "./backgroundImage.jpg";
import logo from "./LOGO-TUTUM.png";

const Home = () => {
  const ENDPOINT = process.env.REACT_APP_URL_BASE;

  const [version, setVersion] = useState("");
  const [frase, setFrase] = useState("");

  let changePath = useNavigate();
  const theme = createTheme({
    typography: {
      body1: {
        fontSize: 12,
        fontWeight: 500,
        fontStyle: "italic",
        color: "#ffffff"
      }
    }
  });
  const handleInternet = () => {
    changePath("/calificaciones");
  };
  const divStyle = {
    //width: "99%",
    height: "100%",
    backgroundImage: `url(${backgroundImage})`,
    backgroundSize: "cover"
  };
  const divStyle2 = {
    //width: "99%",
    height: "21px",
    backgroundImage: `url(${logo})`
  };
  let hed = new Headers();
  hed.append("messageUUID", "12");
  hed.append("requestDate", "19-07-2022");
  hed.append("sendBy", "2");
  hed.append("version", "1");
  hed.append("ipClient", "12");
  hed.append("ipServer", "122");
  hed.append("user", "yo");
  hed.append("Access-Control-Allow-Origin", "*");
  hed.append("Access-Control-Allow-Headers", "*");
  hed.append("Content-Type", "application/json");
  const fetchApi = new APIfetchApi();
  const getInit = async () => {
    const resultado = await fetchApi.fetchApi(
      hed,
      "GET",
      undefined,
      ENDPOINT + "/version"
    );
    const result = await resultado.json();
    setVersion(result);
  };
  useEffect(() => {
    getInit();
  }, []);

  return (
    <div style={divStyle}>
      <Card
        showHeader={false}
        sx={{
          display: "inline-block",
          mx: "45%",
          my: "18%",
          transform: "scale(2)",
          backgroundColor: "transparent"
        }}
        style={{ border: "none", boxShadow: "none" }}
        alignContent={"center"}
      >
        <Stack
          direction="column"
          justifyContent="center"
          alignItems="center"
          spacing={2}
        >
          <Stack>
            <div className="logo">
              <img
                src={logo}
                width="200px"
              ></img>
            </div>
          </Stack>
          <Stack>
            <ThemeProvider theme={theme}>
              <Typography>"Demo de Control Escolar"</Typography>
            </ThemeProvider>
          </Stack>
          <Stack>
            <Card
              showHeader={false}
              sx={{
                display: "inline-block",
                backgroundColor: "transparent",
                borderColor: "#fffffff",
                boxShadow: 20
              }}
              alignContent={"center"}
            >
              <Button className="p-button-outlined" onClick={handleInternet}>
                <Typography
                  variant="h7"
                  noWrap
                  component="div"
                  sx={{
                    fontWeight: "bold",
                    fontStyle: "italic"
                  }}
                >
                  Comenzar &nbsp;
                </Typography>
              </Button>
            </Card>
          </Stack>
        </Stack>
      </Card>
      <Paper
        sx={{
          marginTop: "calc(10% + 60px)",
          width: "100%",
          position: "fixed",
          bottom: 0,
          width: "100%"
        }}
        component="footer"
        square
        variant="outlined"
      >
        <Container maxWidth="lg">
          <Box
            sx={{
              flexGrow: 1,
              justifyContent: "center",
              display: "flex",
              my: 1
            }}
          ></Box>

          <Box
            sx={{
              flexGrow: 1,
              justifyContent: "center",
              display: "flex",
              mb: 2
            }}
          >
            <Typography variant="caption" color="initial">
              Develp by : Efrain Hernandez :: Demo {version}
            </Typography>
          </Box>
        </Container>
      </Paper>
    </div>
  );
};
export default Home;
