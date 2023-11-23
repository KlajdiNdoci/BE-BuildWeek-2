import { Col, ListGroup, Row } from "react-bootstrap";
import { Envelope } from "react-bootstrap-icons";
import { Link } from "react-router-dom";

const Lista = ({ listaClienti }) => {
  return (
    listaClienti &&
    listaClienti.map((elem, index) => (
      <ListGroup.Item key={index}>
        <Row className="d-flex justify-content-between align-items-center">
          <Col xs={2} className="text-start">
            <div>{elem.nomeContatto}</div>
          </Col>
          <Col xs={2} className="text-start">
            <div>{elem.fatturatoAnnuale}</div>
          </Col>
          <Col xs={3} className="text-start">
            <div>{elem.telefonoContatto}</div>
          </Col>
          <Col xs={4} className="text-start">
            <div>{elem.emailContatto}</div>
          </Col>
          <Col xs={1} className="text-start">
            <div>
              <Link to={`/email/${elem.id}`}>
                <Envelope />
              </Link>
            </div>
          </Col>
        </Row>
      </ListGroup.Item>
    ))
  );
};

export default Lista;
