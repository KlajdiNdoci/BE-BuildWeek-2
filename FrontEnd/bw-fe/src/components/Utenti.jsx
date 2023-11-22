import { useEffect, useState } from "react";
import { Col, Container, ListGroup, Pagination, Row } from "react-bootstrap";
import Lista from "./Lista";
import { isDisabled } from "@testing-library/user-event/dist/utils";

const Utenti = () => {
  const [listaUtenti, setListaUtenti] = useState();
  const [pagina, setPagina] = useState(1);
  const [numPagine, setNumPagine] = useState();
  const [ordine, setOrdine] = useState();
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
    </>
  );
};
export default Utenti;
