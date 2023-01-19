import React, { useState, useEffect, useRef } from "react";
import { Button } from "primereact/button";
import { Toolbar } from "primereact/toolbar";
import DataTableComponent from "../../components/DataTableComponent";
import DialogComponent from "../../components/DialogComponent";
import DialogCalificaciones from "../../components/DialogCalificaciones";
import AlertComponent from "../../components/AlertComponent";
import { APIfetchApi } from "./../../API/APIfetch";
import { Toast } from "primereact/toast";

const App = () => {
  const ENDPOINT = process.env.REACT_APP_URL_BASE;
  const toast = useRef(null);

  const [displayBasic, setDisplayBasic] = useState(false);
  const [displayBasicAgregar, setDisplayBasicAgregar] = useState(false);
  const [displayBasicVerCalificaciones, setDisplayBasicVerCalificaciones] = useState(false);
  const [visible, setVisible] = useState(false);
  const [idElementDelete, setIdElementDelete] = useState(null);
  const [urlDelete, setUrlDelete] = useState(null);
  const [alumnoId, setAlumnoId] = useState();
  const [nodes, setNodes] = useState([]);
  const [columnas] = useState([
    { campo: "id", encabezado: "ID" },
    { campo: "nombre", encabezado: "Nombre" },
    { campo: "ap_paterno", encabezado: "A. Paterno" },
    { campo: "ap_materno", encabezado: "A. Materno" },
    { campo: "activo", encabezado: "Activo" }
  ]);
  const fieldsInit = [
    {
      nombre: "nombre",
      titulo: "Nombre",
      tipo: "text",
      nombreValue: "",
      minWidth: "20%"
    },
    {
      nombre: "paterno",
      titulo: "A. Paterno",
      tipo: "text",
      paternoValue: "",
      minWidth: "20%"
    },
    {
      nombre: "materno",
      titulo: "A. Materno",
      tipo: "text",
      maternoValue: "",
      minWidth: "20%"
    },
    {
      nombre: "activo",
      titulo: "Activo",
      tipo: "boolean",
      activoValue: "",
      minWidth: "20%"
    }
  ];
  const [fields, setFields] = useState(fieldsInit);
  let hed = new Headers();
  hed.append("Access-Control-Allow-Origin", "*");
  hed.append("Access-Control-Allow-Headers", "*");
  hed.append("Content-Type", "application/json");
  const fetchApi = new APIfetchApi();
  const getInit = async () => {
    const resultado = await fetchApi.fetchApi(
      hed,
      "GET",
      undefined,
      ENDPOINT + "/alumnos"
    );
    const result = await resultado.json();
    setNodes(result);
  };
  useEffect(() => {
    getInit();
  }, []);

  const actionTemplate = (node, column) => {
    return (
      <>
        <Button
          type="button"
          icon="pi pi-pencil"
          className=""
          style={{ marginRight: ".5em" }}
          onClick={() => handlerVerCalificaciones(node)}
        />
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

  const rightContents = (
    <Button
      label="Alta de Alumno"
      icon="pi pi-plus"
      onClick={() => {
        setFields(
          fields.map((element) => {
            element[element.nombre + "Value"] = "";
            return element;
          })
        );
        setDisplayBasicAgregar(true);
      }}
      className="mr-2"
    />
  );

  const leftContents = <h2>Control Escolar</h2>;

  const header = <Toolbar left={leftContents} right={rightContents} />;

  const handlerAgregar = async (data, method) => {

    const body = {
      nombre: data.filter((element) => element.nombre === "nombre")[0].nombreValue,
      ap_paterno: data.filter((element) => element.nombre === "paterno")[0].paternoValue,
      ap_materno: data.filter((element) => element.nombre === "materno")[0].maternoValue,
      activo: data.filter((element) => element.nombre === "activo")[0].activoValue ? '1' : '0',
    };

    const consultStatus = await fetchApi.fetchApi(
      hed,
      "POST",
      body,
      ENDPOINT + "/alumno"
    );
    if (consultStatus.status === 200) {
      toast.current.show({
        severity: "success",
        summary: "Confirmado",
        detail: "Exito",
        life: 3000
      });
      setDisplayBasicAgregar(false);
      setDisplayBasic(false);
      getInit();
    } else {
      toast.current.show({
        severity: "warn",
        summary: "Error",
        detail: "Hubo un error al invocar servicio",
        life: 3000
      });
    }
  };

  const handlerElementDelete = (node) => {
    setIdElementDelete(node.id);
    setVisible(true);
    setUrlDelete(ENDPOINT + "/alumno/" + node.id);
  };

  const handlerVerCalificaciones = (node) => {
    setIdElementDelete(node.id);
    setAlumnoId(node.id);
    setDisplayBasicVerCalificaciones(true);
  };

  const handlerCloseDialog = () => {
    console.log("CERRAR...............");
    setFields(fieldsInit);
    console.log(fields);
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
        updateIndexTable={getInit}
      />
      <DialogComponent
        displayBasic={displayBasicAgregar}
        setDisplayBasic={setDisplayBasicAgregar}
        header="Agregar Configuracion"
        fields={fields}
        colorButton="green"
        labelButton="Agregar"
        width="50vw"
        sendRow={handlerAgregar}
        handlerClose={handlerCloseDialog}
        fieldsInit={fieldsInit}
      />
      <DialogCalificaciones
        nodes={nodes}
        idAlumno={alumnoId}
        idElementDelete={idElementDelete}
        displayBasic={displayBasicVerCalificaciones}
        setDisplayBasic={setDisplayBasicVerCalificaciones}
        header="Calificaciones"
        fields={fields}
      />
      <DataTableComponent
        nodes={nodes}
        header={header}
        actionTemplate={actionTemplate}
        fields={fields}
        columnas={columnas}
      />
    </>
  );
};

export default App;
