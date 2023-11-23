import { useEffect, useState } from "react";
import { Button, Col, Container, Form, ListGroup, Modal, Pagination, Row } from "react-bootstrap";
import Lista from "./Lista";
import { isDisabled } from "@testing-library/user-event/dist/utils";
import { Link } from "react-router-dom";
import { Envelope } from "react-bootstrap-icons";

const Utenti = () => {
  const [listaUtenti, setListaUtenti] = useState();
  const [pagina, setPagina] = useState(1);
  const [numPagine, setNumPagine] = useState();
  const [ordine, setOrdine] = useState();
  const [show, setShow] = useState(false);
  const [showFilter, setShowFilter] = useState(false);
  const [search, setSearch] = useState();
  const [nomeFiltro, setNomeFiltro] = useState();
  const [nomePH, setNomePH] = useState();
  const [nomeFunzione, setNomeFunzione] = useState();
  const getUtenti = async (order, p) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    console.log(aut.accessToken);
    try {
      console.log(pagina);
      const risp = await fetch(`http://localhost:3001/clienti?order=${order}&page=${p - 1}&size=10`, {
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
        console.log(data.content);
        setListaUtenti(data.content);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleShowFilter = (page) => setShowFilter(true);
  const handleCloseFilter = (page) => setShowFilter(false);
  const [filter, setFilter] = useState();
  const filterClienti = async (page) => {
    const aut = JSON.parse(localStorage.getItem("token"));
    try {
      const risp = await fetch(`http://localhost:3001/clienti/filter?page=${page - 1}&${filter}=${search}`, {
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
                  setNomeFunzione("getUtenti");
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
                  setNomeFunzione("getUtenti");
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
                  setNomeFunzione("getUtenti");
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
                  setNomeFunzione("getUtenti");
                  getUtenti("dataUltimoContatto", 1);
                }}
              >
                Data di ultimo contatto
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setPagina(1);
                  setNomeFunzione("getByprovincia");
                  getByprovincia(1);
                }}
              >
                Provincia sede legale
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setSearch("");
                  setPagina(1);
                  setNomeFunzione("findAllByProvincia");
                  handleShow();
                }}
              >
                Cerca provincia
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setSearch("");
                  setPagina(1);
                  setFilter("fatturatoAnnuale");
                  setNomeFiltro("Fatturato annuale");
                  setNomePH("Scrivi un numero");
                  setNomeFunzione("filterClienti");
                  handleShowFilter();
                }}
              >
                Filtra per fatturato annuale
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setSearch("");
                  setPagina(1);
                  setFilter("dataInserimento");
                  setNomeFiltro("Data di inserimento");
                  setNomePH("yyyy-MM-dd");
                  setNomeFunzione("filterClienti");
                  handleShowFilter();
                }}
              >
                Filtra per data di inserimento
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setSearch("");
                  setPagina(1);
                  setFilter("dataUltimoContatto");
                  setNomeFiltro("Data di ultimo contatto");
                  setNomePH("yyyy-MM-dd");
                  setNomeFunzione("filterClienti");
                  handleShowFilter();
                }}
              >
                Filtra per data di ultimo contatto
              </li>
              <li
                style={{ cursor: "pointer" }}
                onClick={() => {
                  setSearch("");
                  setPagina(1);
                  setFilter("nome");
                  setNomeFiltro("Parte del nome");
                  setNomePH("Scrivi un nome o solo una parte");
                  setNomeFunzione("filterClienti");
                  handleShowFilter();
                }}
              >
                Filtra per parte del nome
              </li>
            </ul>
          </Col>
          <Col xs={8} className="d-flex flex-column justify-content-center align-items-center">
            {listaUtenti && (
              <>
                <ListGroup className="mt-5 w-100 text-center">
                  <ListGroup.Item className="bg-light">
                    <Row className="d-flex justify-content-between align-items-center">
                      <Col xs={2} className="text-start">
                        <span style={{ fontWeight: "bold" }}>Nome</span>
                      </Col>
                      <Col xs={2} className="text-start">
                        <span style={{ fontWeight: "bold" }}>Fatturato</span>
                      </Col>
                      <Col xs={3} className="text-start">
                        <span style={{ fontWeight: "bold" }}>Telefono</span>
                      </Col>
                      <Col xs={4} className="text-start">
                        <span style={{ fontWeight: "bold" }}>Email</span>
                      </Col>
                      <Col xs={1} className="text-start">
                        <span>
                          <Link to={`#`}>
                            <Envelope />
                          </Link>
                        </span>
                      </Col>
                    </Row>
                  </ListGroup.Item>
                  <Lista listaClienti={listaUtenti} />
                </ListGroup>
                <Pagination className="mt-3">
                  {pagina === 1 ? (
                    <Pagination.Prev disabled />
                  ) : (
                    <Pagination.Prev
                      onClick={() => {
                        setPagina(pagina - 1);
                        if (nomeFunzione === "getUtenti") {
                          getUtenti(ordine, pagina - 1);
                        } else if (nomeFunzione === "getByprovincia") {
                          getByprovincia(pagina - 1);
                        } else if (nomeFunzione === "findAllByProvincia") {
                          findAllByProvincia(pagina - 1);
                        } else if (nomeFunzione === "filterClienti") {
                          filterClienti(pagina - 1);
                        }
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
                        if (nomeFunzione === "getUtenti") {
                          getUtenti(ordine, pagina + 1);
                        } else if (nomeFunzione === "getByprovincia") {
                          getByprovincia(pagina + 1);
                        } else if (nomeFunzione === "findAllByProvincia") {
                          findAllByProvincia(pagina + 1);
                        } else if (nomeFunzione === "filterClienti") {
                          filterClienti(pagina + 1);
                        }
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

      <Modal show={showFilter} onHide={handleCloseFilter}>
        <Modal.Header closeButton>
          <Modal.Title>{nomeFiltro}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form
            onSubmit={(e) => {
              e.preventDefault();
              filterClienti(1);
            }}
          >
            <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>Filtra</Form.Label>
              {filter === "dataInserimento" || filter === "dataUltimoContatto" ? (
                <Form.Control
                  type="date"
                  placeholder={nomePH}
                  autoFocus
                  value={search}
                  onChange={(e) => {
                    setSearch(e.target.value);
                  }}
                />
              ) : (
                <Form.Control
                  type="text"
                  placeholder={nomePH}
                  autoFocus
                  value={search}
                  onChange={(e) => {
                    setSearch(e.target.value);
                  }}
                />
              )}
            </Form.Group>
            <Modal.Footer>
              <Button
                variant="secondary"
                onClick={() => {
                  handleCloseFilter();
                  setSearch("");
                }}
              >
                Close
              </Button>
              <Button
                type="submit"
                variant="primary"
                onClick={() => {
                  handleCloseFilter();
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
