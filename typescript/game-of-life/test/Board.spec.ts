import {expect} from "chai";
import {Board} from "../src/Board";
import {Position} from "../src/Position";

describe("Board", () => {
    let target: Board;

    beforeEach(() => target = new Board());

    it("should have all cells initially dead", () => {
        expect(isCellAliveAt(new Position(1, 1))).to.be.false;
        expect(isCellAliveAt(new Position(2, 0))).to.be.false;
    });

    it("should allow cells to be set to alive", () => {
        target.setCellsAliveAt(
            new Position(1, 1),
            new Position(1, 2),
            new Position(5, 3),
        );
        expect(isCellAliveAt(new Position(1, 2))).to.be.true;
    });

    it("should memorize the position of the cell that is alive", () => {
        target.setCellsAliveAt(new Position(1, 2));
        expect(isCellAliveAt(new Position(1, 1))).to.be.false;
    });

    it("should count living neighbours of cell without neighbours correctly", () => {
        target.setCellsAliveAt(new Position(1, 1));
        expect(target.countLivingNeighboursOf(new Position(3, 6))).to.equal(0);
    });

    it("should count living neighbours of cell surrounded by neighbours correctly", () => {
        target.setCellsAliveAt(
            new Position(0, 0), new Position(1, 0), new Position(2, 0),
            new Position(0, 1), new Position(2, 1),
            new Position(0, 2), new Position(1, 2), new Position(2, 2),
        );
        expect(target.countLivingNeighboursOf(new Position(1, 1))).to.equal(8);
    });

    it("should count two living neighbours", () => {
        target.setCellsAliveAt(
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
        );
        expect(target.countLivingNeighboursOf(new Position(0, 0))).to.equal(2);
    });

    it("should count three living neighbours", () => {
        target.setCellsAliveAt(
            new Position(1, 0),
            new Position(1, 1),
            new Position(1, 2),
        );
        expect(target.countLivingNeighboursOf(new Position(0, 1))).to.equal(3);
    });

    it("should let a living cell die", () => {
        target.setCellsAliveAt(new Position(1, 1));
        target.setCellsDeadAt(new Position(1, 1));
        expect(isCellAliveAt(new Position(1, 1))).to.be.false;
    });

    function isCellAliveAt(position: Position) {
        return target.isCellAliveAt(position);
    }
});
