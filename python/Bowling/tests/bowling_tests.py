import unittest

from assertpy import assert_that
from src.bowling import Bowling


class BowlingTests(unittest.TestCase):
    def setUp(self):
        self.bowling = Bowling()

    def test_gutter_game(self):
        self.roll_many(0, 20)
        assert_that(self.bowling.get_score()).is_equal_to(0)

    def test_one_point_game(self):
        self.roll_many(1, 20)
        assert_that(self.bowling.get_score()).is_equal_to(20)

    def roll_many(self, pins: int, times: int) -> None:
        for i in range(0, times):
            self.bowling.roll(pins)
