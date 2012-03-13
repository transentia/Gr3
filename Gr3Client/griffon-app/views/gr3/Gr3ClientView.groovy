package gr3

import static java.awt.BorderLayout.*
import java.awt.Color
import org.jdesktop.swingx.painter.PinstripePainter
import javax.swing.WindowConstants
import org.jdesktop.swingx.painter.GlossPainter
import org.jdesktop.swingx.painter.MattePainter
import org.jdesktop.swingx.painter.CompoundPainter

// need to start in a guaranteed 'non-winning' state
def leftReelIcons = [imageIcon('/reel/card_figures_-_club_01.png'),
        imageIcon('/reel/card_figures_-_diamond_01.png'),
        imageIcon('/reel/attenzione_rotondo_archi_01.png'),
        imageIcon('/reel/card_figures_-_heart_01.png'),
        imageIcon('/reel/card_figures_-_spade_01.png')]
def middleReelIcons = (leftReelIcons[2..-1] + leftReelIcons[0..1])
def rightReelIcons = (middleReelIcons[2..-1] + middleReelIcons[0..1])
def reelIcons = [left: leftReelIcons, middle: middleReelIcons, right: rightReelIcons]

// http://jshingler.blogspot.com/2009/11/griffon-painter-demo.html
def gloss = new GlossPainter(
        paint: new Color(1.0f, 1.0f, 1.0f, 0.2f),
        position: GlossPainter.GlossPosition.TOP)
def stripes = new PinstripePainter(
        paint: new Color(1.0f, 1.0f, 1.0f, 0.17f),
        spacing: 5.0)
def matte = new MattePainter(fillPaint: new Color(51, 51, 51))

//def pinstripe = new PinstripePainter(Color.LIGHT_GRAY)

application(title: 'Gr3Client',
        size: [320, 740],
        resizable: false,
        defaultCloseOperation: WindowConstants.DO_NOTHING_ON_CLOSE,
        windowClosing: controller.exitAction,
        locationByPlatform: true,
        iconImage: imageIcon('/griffon-icon-48x48.png').image,
        iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                imageIcon('/griffon-icon-32x32.png').image,
                imageIcon('/griffon-icon-16x16.png').image]
) {
  menuBar(mainMenuBar)

  vbox(constraints: NORTH) {
    jxheader(title: "Gr3",
            description: 'Pokie Machine using Griffon, Grails and Groovy.',
            icon: imageIcon('/griffon-icon-48x48.png'),
            border: emptyBorder(4),
            // only if gloss is used:
            descriptionForeground: Color.WHITE,
            titleForeground: Color.RED,
            backgroundPainter: new CompoundPainter(matte, stripes, gloss))
//            backgroundPainter: pinstripe)

    panel() {
      tableLayout(cellpadding: 4) {
        tr {
          td(align: 'left') {
            label('Plays:')
          }
          td(align: 'right') {
            label(id: 'plays', text: bind { model.plays})
          }
          td(align: 'left') {
            label('Played:')
          }
          td(align: 'right') {
            label(id: 'played', text: bind { model.playedValue})
          }
        }
        tr {
          td(align: 'left') {
            label('Earnings:')
          }
          td(align: 'right') {
            label(id: 'earnings', text: bind { model.earnings})
          }
          td(align: 'left') {
            label('Totalizer:')
          }
          td(align: 'right') {
            label(id: 'totalizer', text: bind { model.totalizerValue})
          }
        }
      }
    }
  }

  panel(new Spindle(), id: 'spindle', border: loweredBevelBorder(), constraints: CENTER) {
    borderLayout()
    tableLayout(cellpadding: 4) {
      tr {
        for (reel in ['left', 'middle', 'right']) {
          td(align: 'center') {
            widget(id: "${reel}Reel".toString(), new Reel(icons: reelIcons[reel]))
          }
        }
      }
    }
  }

  panel(constraints: SOUTH) {
    tableLayout(cellpadding: 4) {
      tr {
        buttonGroup(id: 'playValue').with {group ->
          for (v in [1, 2, 5, 10, 20, 50])
            td { radioButton(text: "\$$v".toString(), buttonGroup: group, actionCommand: "$v", selected: v == 20) }
        }
      }
      tr {
        td(colspan: 6, align: 'center') {
          label(id: 'message', text: bind { model.message })
        }
      }
      tr {
        td(colspan: 6, align: 'center') {
          button(playAction, id: 'playButton')
        }
      }
    }
  }
}
