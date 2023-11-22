import { useEffect, useState } from "react";
import { Button, Col, Container, Form, ListGroup, Modal, Pagination, Row } from "react-bootstrap";
import Lista from "./Lista";
import { isDisabled } from "@testing-library/user-event/dist/utils";

const Utenti = () => {
  const [listaUtenti, setListaUtenti] = useState();
  const [pagina, setPagina] = useState(1);
  const [numPagine, setNumPagine] = useState();
  const [ordine, setOrdine] = useState();
  const [show, setShow] = useState(false);
  const [search, setSearch] = useState();
  const getUtenti = async (order, p) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      console.log(pagina);
      const risp = await fetch(`http://localhost:3001/clienti?order=${order}&page=${p - 1}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        console.log(data);
        setNumPagine(data.totalPages);
        console.log(data.content);
        setListaUtenti(data.content);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const getByprovincia = async (page) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    try {
      const risp = await fetch(`http://localhost:3001/clienti/get_all_order_by_provincia?page=${page - 1}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        console.log(data);
        setNumPagine(data.totalPages);
        console.log(data.content);
        setListaUtenti(data.content);
      }
    } catch (error) {
      console.log(error);
    }
  };
  const handleShow = (page) => setShow(true);
  const handleClose = (page) => setShow(false);
  const findAllByProvincia = async (page) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    try {
      const risp = await fetch(`http://localhost:3001/clienti/get_all_by_provincia?page=${page - 1}&prov=${search}`, {
        method: "GET",
        headers: {
          "content-type": "application/json",
          Authorization: `Bearer ${aut.accessToken}`,
        },
      });
      if (risp.ok) {
        const data = await risp.json();
        console.log(data);
        setNumPagine(data.totalPages);
        setSearch("");
        console.log(data.content);
        setListaUtenti(data.content);
      }
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <>
      <Container>
        <Row>
          <Col xs={4}>
            <ul className="mt-5">
              Mostra clienti:
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  setOrdine("nomeContatto");
                  getUtenti("nomeContatto", 1);
                }}
              >
                Nome
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  setOrdine("fatturatoAnnuale");
                  getUtenti("fatturatoAnnuale", 1);
                }}
              >
                Fatturato
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  setOrdine("dataInserimento");
                  getUtenti("dataInserimento", 1);
                }}
              >
                Data di inserimento
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  setOrdine("dataUltimoContatto");
                  getUtenti("dataUltimoContatto", 1);
                }}
              >
                Data di ultimo contatto
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  getByprovincia(1);
                }}
              >
                Provincia sede legale
              </li>
              <li style={{ cursor: "pointer" }} onClick={handleShow}>
                cerca provincia
              </li>
            </ul>
          </Col>
          <Col xs={8} className="d-flex flex-column justify-content-center align-items-center">
            {listaUtenti && (
              <>
                <ListGroup className="mt-5 w-100 text-center">
                  <Lista listaClienti={listaUtenti} />
                </ListGroup>
                <Pagination className="mt-3">
                  {pagina === 1 ? (
                    <Pagination.Prev disabled />
                  ) : (
                    <Pagination.Prev
                      onClick={() => {
                        setPagina(pagina - 1);
                        getUtenti(ordine, pagina - 1);
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
                        getUtenti(ordine, pagina + 1);
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
              findAllByProvincia(1);
            }}
          >
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Provincia</Form.Label>
              <Form.Control
                type="text"
                placeholder="provincia"
                autoFocus
                value={search}
                onChange={(e) => {
                  setSearch(e.target.value);
                }}
              />
            </Form.Group>
            <Modal.Footer>
              {" "}
              <Button
                variant="secondary"
                onClick={() => {
                  handleClose();
                  setSearch("");
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
export default Utenti;
