import { Col, ListGroup, Row } from "react-bootstrap";

const ListaFatture = ({ listaFatture }) => {
  return (
    listaFatture &&
    listaFatture.map((elem, index) => (
      <ListGroup.Item key={index}>
        <Row xs={4} className="d-flex justify-content-between align-items-center">
          <Col className="text-start">
            <div>{elem.numeroFattura}</div>
          </Col>
          <Col className="text-start">
            <div>{elem.statoFattura}</div>
          </Col>
          <Col className="text-start">
            <div>{elem.importo}</div>
          </Col>
          <Col className="text-start">
            <div>{elem.data}</div>
          </Col>
        </Row>
      </ListGroup.Item>
    ))
  );
};
export default ListaFatture;
