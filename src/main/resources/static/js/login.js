$(document).ready(function () {
  $(".message a").click(function () {
    $("form").animate(
      {
        height: "toggle",
        padding: "toggle",
        margin: "toggle",
        opacity: "toggle",
      },
      "slow"
    );
  });
});
