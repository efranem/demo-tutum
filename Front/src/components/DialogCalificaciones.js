import React, { useEffect, useRef, useState } from "react";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { Calendar } from "primereact/calendar";
import { ToggleButton } from "primereact/togglebutton";
import DataTableComponent from "./DataTableComponent";
import { APIfetchApi } from "../API/APIfetch";
import { Toast } from "primereact/toast";
import AlertComponent from "../components/AlertComponent";
import { FormControl, InputLabel, MenuItem } from "@mui/material";
import { Dropdown } from 'primereact/dropdown';
import { InputNumber } from 'primereact/inputnumber';

const DialogComponent = ({ ...props }) => {

  const ENDPOINT = process.env.REACT_APP_URL_BASE;

  const toast = useRef(null);

  const [varInfo, setVarInfo] = useState(props.fields);
  const [botonBloqueado, setBotonBloqueado] = useState(false);
  const [nodes, setNodes] = useState([]);
  const [promedio, setPromedio] = useState();
  const [materias, setMaterias] = useState([]);
  const [materia, setMateria] = useState();
  const [calificacion, setCalificacion] = useState();

  const [visible, setVisible] = useState(false);
  const [idElementDelete, setIdElementDelete] = useState(null);
  const [urlDelete, setUrlDelete] = useState(null);

  const [columnas] = useState([
    { campo: "id_t_usuario", encabezado: "ID Alumno" },
    { campo: "nombre", encabezado: "Nombre" },
    { campo: "apellido", encabezado: "A. Paterno" },
    { campo: "materia", encabezado: "Materia" },
    { campo: "calificacion", encabezado: "Calificacion" },
    { campo: "fecha_registro", encabezado: "Fecha Registro" }
  ]);

  let hed = new Headers();
  hed.append("Access-Control-Allow-Origin", "*");
  hed.append("Access-Control-Allow-Headers", "*");
  hed.append("Content-Type", "application/json");
  const fetchApi = new APIfetchApi();
  const getInitCalificaciones = async () => {
    const resultado = await fetchApi.fetchApi(
      hed,
      "GET",
      undefined,
      ENDPOINT + "/calificacion/"+props.idAlumno      
    );
    const result = await resultado.json();
    setNodes(result.calificaciones);
    setPromedio(result.promedio);
  };
  const getInitMaterias = async () => {
    const resultado = await fetchApi.fetchApi(
      hed,
      "GET",
      undefined,
      ENDPOINT + "/materias"
    );
    const result = await resultado.json();
    setMaterias(result);
  };
  useEffect(() => {
    console.log("load calificaciones");
    getInitCalificaciones();
    getInitMaterias();
  }, [props.idAlumno]);

  const onMateriaChange = (e) => {
    setMateria(e.value);
  }

  const onChangeCalificacion = (e) => {
    const { name, value } = e.target;
    setCalificacion(value);
  }

  const onValChange = (event) => {
    const { name, value } = event.target;
    const fieldArray = varInfo;
    setVarInfo(
      fieldArray.map((element) => {
        if (element.hasOwnProperty(name)) {
          element[name] = value;
          element.key = value;
        }
        return element;
      })
    );
  };

  const handlerClick = () => {
    props.sendRow(varInfo);
  };

  
  const actionTemplate = (node, column) => {
    return (
      <>
        <Button
          type="button"
          icon="pi pi-trash"
          className="p-button-danger"
          style={{ marginRight: ".5em" }}
          onClick={() => handlerElementDelete(node)}
        />
      </>
    );
  };

  const handlerElementDelete = (node) => {
    setIdElementDelete(node.id);
    setVisible(true);
    setUrlDelete(ENDPOINT + "/calificacion/" + node.id);
  };

  const handlerAgregarCalificacion = async (data, method) => {
    const body = {
      idAlumno: props.idAlumno,
      idMateria: materia.id,
      calificacion: calificacion
    };
    console.log(body);
    console.log(data);

    const consultStatus = await fetchApi.fetchApi(
      hed,
      "POST",
      body,
      ENDPOINT + "/calificacion"
    );
    if (consultStatus.status === 200) {
      toast.current.show({
        severity: "success",
        summary: "Confirmado",
        detail: "Exito",
        life: 3000
      });
      getInitCalificaciones();
    } else {
      console.log(consultStatus);
      toast.current.show({
        severity: "warn",
        summary: "Error",
        detail: "La materia ya se encuentra evaluada, modifique el registro correspondiente",
        life: 3000
      });
    }
  };

  return (
    <>
      <Toast ref={toast} />
      <AlertComponent
        visible={visible}
        encabezado="Confirmacion"
        mensaje="¿Seguro quieres eliminar el registro?"
        icono="pi pi-info-circle"
        setVisible={setVisible}
        acceptClassName="p-button-danger"
        idElementDelete={idElementDelete}
        urlDelete={urlDelete}
        headers={hed}
        updateIndexTable={getInitCalificaciones}
      />
      <Dialog
          header={props.header}
          visible={props.displayBasic}
          modal
          style={{ width: props.width }}
          onHide={() => {
            props.setDisplayBasic(false);
          }}
        >

          <div className="p-inputgroup">
            <span
              className="p-inputgroup-addon"
              style={{
                minWidth: "20%"
              }}>MATERIA</span>
            {
              <Dropdown value={materia} 
              options={materias}
              optionLabel="nombre" 
              placeholder="Seleccionar Materia"
              onChange={onMateriaChange}
              />            
            }      
            <span
              className="p-inputgroup-addon"
              style={{
                minWidth: "20%"
              }}>CALIFICACION</span>   
            {
              <InputNumber
              value={calificacion}
              disabled={false}
              onValueChange={onChangeCalificacion}
              mode="decimal"
              minFractionDigits={1}
              min={0} max={10}
              /> 
            }
            <Button
              type="button"
              icon="pi pi-plus"
              label="Agregar"
              onClick={() => handlerAgregarCalificacion()}
            />   
          </div>  
          {
            <DataTableComponent
              nodes={nodes}
              columnas={columnas}
              actionTemplate={actionTemplate}
            />
          }
          <span style={{marginBottom: "20px"}}></span>
          <div className="p-inputgroup">
            <span
              className="p-inputgroup-addon"
              style={{
                minWidth: "50%"
              }}>PROMEDIO</span>
            {
              <InputText
              name={"PromedioValue"}
              value={promedio}
              onChange={onValChange}
              disabled={false}               
              />                
            }              
          </div>

        </Dialog>
        
    </>
   
  );
};

export default DialogComponent;
