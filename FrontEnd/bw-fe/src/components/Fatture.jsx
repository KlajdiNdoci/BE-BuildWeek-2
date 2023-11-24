import { Container, Row, Col, ListGroup, Pagination, Button, Modal, Form } from "react-bootstrap";
import Lista from "./Lista";
import { useState } from "react";
import ListaFatture from "./ListaFatture";

const Fatture = () => {
  const [pagina, setPagina] = useState(1);
  const [numPagine, setNumPagine] = useState();
  const [listaFatture, setListaFatture] = useState();
  const [show, setShow] = useState();
  const [req, setReq] = useState();
  const [req2, setReq2] = useState();
  const [search, setSearch] = useState();
  const [search2, setSearch2] = useState();
  const handleShow = (page) => setShow(true);
  const handleClose = (page) => setShow(false);
  const getFatture = async (request, p) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      console.log(request);
      const risp = await fetch(`http://localhost:3001/fatture/requests?page=${p - 1}&${request}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      console.log(risp);
      if (risp.ok) {
        console.log("sono dentro if");
        const data = await risp.json();
        console.log(data);
        setNumPagine(data.totalPages);
        setSearch("");
        setSearch2("");
        setListaFatture(data.content);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <Container fluid className="pt-5">
        <Row>
          <Col xs={3}>
            <div className="inserimento_dati mx-auto border p-4 mt-3 shadow bg-light" style={{ borderRadius: "20px" }}>
              <ul className="">
                <span style={{ fontSize: "25px", fontFamily: "italic" }}>Mostra fatture:</span>
                <li
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    setReq("id_cliente=");
                    setPagina(1);
                    handleShow();
                  }}
                  className="mt-3"
                >
                  Cliente
                </li>
                <li
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    setReq("statoFattura=");
                    setPagina(1);
                    handleShow();
                  }}
                  className="mt-1"
                >
                  Stato
                </li>
                <li
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    setReq("data=");
                    setPagina(1);
                    handleShow();
                  }}
                  className="mt-1"
                >
                  Data
                </li>
                <li
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    setReq("year=");
                    setPagina(1);
                    handleShow();
                  }}
                  className="mt-1"
                >
                  Anno
                </li>
                <li
                  style={{ cursor: "pointer" }}
                  onClick={() => {
                    setReq("imp1=");
                    setReq2("imp2=");
                    setPagina(1);
                    handleShow();
                  }}
                  className="mt-1"
                >
                  Range di importi
                </li>
              </ul>
            </div>
          </Col>
          <Col xs={9} className="d-flex flex-column justify-content-start align-items-center">
            {listaFatture && (
              <>
                <ListGroup className="mt-3 w-100 text-center shadow">
                  <ListGroup.Item className="bg-light">
                    <Row xs={4} className="d-flex justify-content-between align-items-center">
                      <Col className="text-start">
                        <span style={{ fontWeight: "bold" }}>N° Fattura</span>
                      </Col>
                      <Col className="text-start">
                        <span style={{ fontWeight: "bold" }}>Stato</span>
                      </Col>
                      <Col className="text-start">
                        <span style={{ fontWeight: "bold" }}>Importo</span>
                      </Col>
                      <Col className="text-start">
                        <span style={{ fontWeight: "bold" }}>Data</span>
                      </Col>
                    </Row>
                  </ListGroup.Item>
                  <ListaFatture listaFatture={listaFatture} />
                </ListGroup>
                <Pagination className="mt-3">
                  {pagina === 1 ? (
                    <Pagination.Prev disabled />
                  ) : (
                    <Pagination.Prev
                      onClick={() => {
                        setPagina(pagina - 1);
                        getFatture(req.substring(0, 5) === "imp1=" ? `${req}&${req2}` : req, pagina - 1);
                      }}
                    />
                  )}
                  <Pagination.Item id="page">{pagina}</Pagination.Item>
                  {pagina === numPagine ? (
                    <Pagination.Next disabled />
                  ) : (
                    <Pagination.Next
                      onClick={() => {
                        setPagina(pagina + 1);
                        console.log(req);
                        getFatture(req.substring(0, 5) === "imp1=" ? `${req}&${req2}` : req, pagina + 1);
                      }}
                    />
                  )}
                </Pagination>
              </>
            )}
          </Col>
        </Row>
      </Container>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Modal heading</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            onSubmit={(e) => {
              e.preventDefault();
              console.log(search);
              console.log();
              req === "statoFattura="
                ? setReq((prevReq) => `${prevReq}${search.toUpperCase()}`)
                : setReq((prevReq) => `${prevReq}${search}`);
              req.substring(0, 5) === "imp1=" && setReq2((prevReq2) => `${prevReq2}${search2}`);
              req.substring(0, 5) === "imp1="
                ? getFatture(`${req}${search}&${req2}${search2}`, 1)
                : getFatture(`${req}${search.toUpperCase()}`, 1);
            }}
          >
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>{req && req.substring(0, req.length - 1).toUpperCase()}</Form.Label>
              <Form.Control
                type={req && req.substring(0, 5) === "data=" ? "date" : "text"}
                placeholder={req && req.substring(0, req.length - 1)}
                autoFocus
                value={search}
                onChange={(e) => {
                  setSearch(e.target.value);
                }}
              />

              {req === "imp1=" && (
                <Form.Control
                  className="mt-3"
                  type="text"
                  placeholder={req2 && req2.substring(0, req2.length - 1)}
                  value={search2}
                  onChange={(e) => {
                    setSearch2(e.target.value);
                  }}
                />
              )}
            </Form.Group>
            <Modal.Footer>
              {" "}
              <Button
                variant="secondary"
                onClick={() => {
                  handleClose();
                  setSearch("");
                  setSearch2("");
                }}
              >
                Close
              </Button>
              <Button
                type="submit"
                variant="primary"
                onClick={() => {
                  handleClose();
                }}
                className="ms-3"
              >
                Search
              </Button>
            </Modal.Footer>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};
export default Fatture;
