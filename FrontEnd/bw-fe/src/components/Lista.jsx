import { Col, ListGroup, Row } from "react-bootstrap";
import { Envelope } from "react-bootstrap-icons";
import { Link } from "react-router-dom";
import ClientiDetail from "./ClientiDetail";
import { useState } from "react";

const Lista = ({ listaClienti }) => {
  const [modalShow, setModalShow] = useState(false);
  const [elem, setElem] = useState();
  return (
    <>
      {listaClienti &&
        listaClienti.map((elem, index) => (
          <ListGroup.Item
            key={index}
            onClick={() => {
              setElem(elem);
              setModalShow(true);
            }}
          >
            <Row className="d-flex justify-content-between align-items-center">
              <Col xs={2} className="text-start">
                <div className="text-truncate" style={{ cursor: "pointer" }}>
                  {elem.nomeCliente}
                </div>
              </Col>
              <Col xs={2} className="text-start">
                <div>{elem.fatturatoAnnuale}</div>
              </Col>
              <Col xs={3} className="text-start">
                <div>{elem.telefono}</div>
              </Col>
              <Col xs={4} className="text-start">
                <div>{elem.email}</div>
              </Col>
              <Col xs={1} className="text-start">
                <div id="envelop">
                  <Link to={`/email/send/${elem.id}`}>
                    <Envelope />
                  </Link>
                </div>
              </Col>
            </Row>
          </ListGroup.Item>
        ))}
      <ClientiDetail show={modalShow} elem={elem} onHide={() => setModalShow(false)} />
    </>
  );
};

export default Lista;
